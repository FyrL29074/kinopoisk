package com.fyrl29074.movieslist.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState.Loading
import com.fyrl29074.movieslist.databinding.FragmentMovieListBinding
import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider
import com.fyrl29074.movieslist.presentation.viewModel.MovieListViewModel
import com.fyrl29074.movieslist.presentation.viewModel.MovieListViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesListComponent: MoviesListComponent

    private val adapter = MovieListAdapter()

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
    }

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
        setupUI()
//
//        viewModel.pagedMovies.onEach { pagingData ->
//            adapter.submitData(pagingData)
//        }.launchIn(viewLifecycleOwner.lifecycleScope)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is Loading
                    binding.appendProgress.isVisible = it.source.append is Loading
                }
            }
        }

        lifecycleScope.launch {
            // We repeat on the STARTED lifecycle because an Activity may be PAUSED
            // but still visible on the screen, for example in a multi window app
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagedMovies.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun setupUI() {
        binding.recyclerViewMoviesList.adapter = adapter
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