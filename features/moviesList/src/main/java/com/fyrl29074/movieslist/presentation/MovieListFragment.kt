package com.fyrl29074.movieslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fyrl29074.movieslist.databinding.FragmentMovieListBinding
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleState(state: State) {
        viewLifecycleOwner.lifecycleScope.launch {
            when (state) {
                is State.Error -> {
                    binding.progressBar.isVisible = false
                    // TODO: show error message
                }
                is State.Loaded -> {
                    binding.progressBar.isVisible = false
                    binding.test.text = state.data.toString()
                }
                State.Loading -> {
                    binding.progressBar.isVisible = true
                }
                State.Waiting -> {
                    viewModel.getMovies()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}