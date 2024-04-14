package com.fyrl29074.moviepage.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fyrl29074.models.domain.Review
import com.fyrl29074.moviepage.domain.GetMovieReviewsUseCase

class ReviewsPagingSource(
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val movieId: Int,
) : PagingSource<Int, Review>(
) {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int = FIRST_PAGE
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: FIRST_PAGE

        return runCatching {
            getMovieReviewsUseCase.execute(
                page = page,
                limit = LIMIT,
                movieId = movieId,
            )
        }.fold(
            onSuccess = { reviews ->
                LoadResult.Page(
                    data = reviews,
                    prevKey = null,
                    nextKey = if (reviews.isEmpty()) null else page + 1
                )
            },
            onFailure = { e ->
                LoadResult.Error(e)
            }
        )
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val LIMIT = 10
    }
}