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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMoviesByPageUseCase: GetMoviesByPageUseCaseImpl,
    private val movieFormatter: MovieFormatter,
) : ViewModel() {

    private var currentRequestScope: CoroutineScope? = null

    private val defaultPager = Pager(
        config = PagingConfig(pageSize = PAGE_LIMIT),
        pagingSourceFactory = { MoviePagingSource(getMoviesByPageUseCase, movieFormatter) }
    )

    private val pagedMoviesFlow = MutableStateFlow(defaultPager.flow.cachedIn(viewModelScope))

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedMovies: Flow<PagingData<MovieVO>> get() = pagedMoviesFlow.flatMapLatest { it }

    // Используется поиск по name, если он null, то поиск по fromYear, toYear, country, ageRating
    fun applyFilters(
        name: String? = null,
        fromYear: Int? = null,
        toYear: Int? = null,
        country: String? = null,
        ageRating: Int? = null,
    ) {

        currentRequestScope?.cancel()

        currentRequestScope = CoroutineScope(Dispatchers.IO).also { scope ->
            scope.launch {
                val newPagingSource = MoviePagingSource(
                    getMoviesByPageUseCase = getMoviesByPageUseCase,
                    movieFormatter = movieFormatter,
                    name = name,
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
        }

//        if (name != null
//            || fromYear != null
//            || toYear != null
//            || country != null
//            || ageRating != null
//        ) {
//
//        }
//
//        val newPagingSource = MoviePagingSource(
//            getMoviesByPageUseCase = getMoviesByPageUseCase,
//            movieFormatter = movieFormatter,
//            name = name,
//            fromYear = fromYear,
//            toYear = toYear,
//            country = country,
//            ageRating = ageRating,
//        )
//
//        pagedMoviesFlow.value = Pager(
//            config = PagingConfig(pageSize = PAGE_LIMIT),
//            pagingSourceFactory = { newPagingSource }
//        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val PAGE_LIMIT = 10
    }
}