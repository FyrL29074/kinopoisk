package com.fyrl29074.movieslist.presentation.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fyrl29074.movieslist.domain.useCase.GetMoviesByPageUseCaseImpl
import com.fyrl29074.movieslist.presentation.formatter.MovieFormatter
import com.fyrl29074.movieslist.presentation.model.MovieVO

class MoviePagingSource (
    private val getMoviesByPageUseCase: GetMoviesByPageUseCaseImpl,
    private val movieFormatter: MovieFormatter,
) : PagingSource<Int, MovieVO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieVO>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieVO> {
        val page = params.key ?: 1
        return runCatching {
            getMoviesByPageUseCase.execute(page, LIMIT)
        }.fold(
            onSuccess = { movies ->
                val moviesVO = movies.map { movie ->
                    movieFormatter.format(movie)
                }
                LoadResult.Page(
                    data = moviesVO,
                    prevKey = null,
                    nextKey = if (moviesVO.isEmpty()) null else page + 1
                )
            },
            onFailure = { e ->
                LoadResult.Error(e)
            }
        )
    }

    private companion object {
        const val FIRST_PAGE = 1
        const val LIMIT = 10
    }
}