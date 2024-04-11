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
import kotlinx.coroutines.flow.Flow

class MovieListViewModel (
    private val getMoviesByPageUseCase: GetMoviesByPageUseCaseImpl,
    private val movieFormatter: MovieFormatter,
) : ViewModel() {

    val pagedMovies: Flow<PagingData<MovieVO>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MoviePagingSource(getMoviesByPageUseCase, movieFormatter) }
    ).flow.cachedIn(viewModelScope)
}