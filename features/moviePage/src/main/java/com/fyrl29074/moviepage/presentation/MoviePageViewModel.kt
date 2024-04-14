package com.fyrl29074.moviepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fyrl29074.models.domain.Review
import com.fyrl29074.models.presentation.formatter.MovieFormatter
import com.fyrl29074.moviepage.domain.GetMovieByIdUseCase
import com.fyrl29074.moviepage.domain.GetMovieReviewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviePageViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val movieFormatter: MovieFormatter,
    private val movieId: Int,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    val pagedReviewsFlow: Flow<PagingData<Review>> = Pager(
        config = PagingConfig(LIMIT),
        pagingSourceFactory = {
            ReviewsPagingSource(
                getMovieReviewsUseCase = getMovieReviewsUseCase,
                movieId = movieId,
            )
        }
    ).flow.cachedIn(viewModelScope)

    fun getMovieById(movieId: Int) {
        _state.value = State.Loading
        viewModelScope.launch {
            runCatching {
                getMovieByIdUseCase.execute(movieId)
            }.fold(
                onSuccess = { movie ->
                    _state.value = State.Loaded(movieFormatter.format(movie))
                },
                onFailure = { e ->
                    _state.value = State.Error(e.message ?: "Unknown error")
                }
            )
        }
    }

    companion object {
        private const val LIMIT = 10
    }
}