package com.fyrl29074.movieslist.presentation.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fyrl29074.movieslist.domain.GetMoviesByFiltersUseCaseImpl
import com.fyrl29074.movieslist.domain.GetMoviesByNameUseCase
import com.fyrl29074.models.presentation.MovieVO
import com.fyrl29074.models.presentation.formatter.MovieFormatter

class MoviePagingSource(
    private val getMoviesByFiltersUseCase: GetMoviesByFiltersUseCaseImpl,
    private val getMoviesByNameUseCase: GetMoviesByNameUseCase,
    private val movieFormatter: MovieFormatter,
    private val name: String? = null,
    private val fromYear: Int? = null,
    private val toYear: Int? = null,
    private val country: String? = null,
    private val ageRating: Int? = null,
) : PagingSource<Int, MovieVO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieVO>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieVO> {
        val page = params.key ?: FIRST_PAGE

        return runCatching {
            if (name != null) {
                getMoviesByNameUseCase.execute(
                    name = name,
                    page = page,
                    limit = LIMIT,
                )
            } else {
                getMoviesByFiltersUseCase.execute(
                    page = page,
                    limit = LIMIT,
                    fromYear = fromYear,
                    toYear = toYear,
                    country = country,
                    ageRating = ageRating,
                )
            }
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