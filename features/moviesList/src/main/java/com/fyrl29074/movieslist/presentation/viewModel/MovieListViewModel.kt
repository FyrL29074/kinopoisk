package com.fyrl29074.movieslist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fyrl29074.movieslist.domain.useCase.GetMoviesByPageUseCaseImpl
import com.fyrl29074.movieslist.presentation.formatter.MovieFormatter
import com.fyrl29074.movieslist.presentation.model.MovieVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class MovieListViewModel(
    private val getMoviesByPageUseCase: GetMoviesByPageUseCaseImpl,
    private val movieFormatter: MovieFormatter,
) : ViewModel() {

    private val defaultPager = Pager(
        config = PagingConfig(pageSize = PAGE_LIMIT),
        pagingSourceFactory = { MoviePagingSource(getMoviesByPageUseCase, movieFormatter) }
    )

    private val pagedMoviesFlow = MutableStateFlow(defaultPager.flow.cachedIn(viewModelScope))

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedMovies: Flow<PagingData<MovieVO>> get() = pagedMoviesFlow.flatMapLatest { it }

    fun applyFilters(
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ) {

        val newPagingSource = MoviePagingSource(
            getMoviesByPageUseCase = getMoviesByPageUseCase,
            movieFormatter = movieFormatter,
            fromYear = fromYear,
            toYear = toYear,
            country = country,
            ageRating = ageRating,
        )

        pagedMoviesFlow.value = Pager(
            config = PagingConfig(pageSize = PAGE_LIMIT),
            pagingSourceFactory = { newPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val PAGE_LIMIT = 10
    }
}