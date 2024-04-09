package com.fyrl29074.movieslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fyrl29074.movieslist.databinding.FragmentMovieListBinding
import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesListComponent: MoviesListComponent

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDagger()
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                state.handle()
            }
        }
    }

    private fun State.handle() {
        when (this) {
            State.Waiting -> {
                viewModel.getMovies()
            }

            State.Loading -> {
                binding.progressBar.isVisible = true
            }

            is State.Loaded -> {
                binding.progressBar.isVisible = false
            }

            is State.Error -> {
                binding.progressBar.isVisible = false
                // TODO: show error message
            }
        }
    }

    private fun setUpDagger() {
        moviesListComponent = (requireActivity().application as MoviesListComponentProvider)
            .provideMoviesListComponentProvider()

        moviesListComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}