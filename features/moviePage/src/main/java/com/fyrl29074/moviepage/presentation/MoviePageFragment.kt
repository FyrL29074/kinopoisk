package com.fyrl29074.moviepage.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fyrl29074.moviepage.R
import com.fyrl29074.moviepage.databinding.FragmentMoviePageBinding
import com.fyrl29074.moviepage.di.MoviePageComponent
import com.fyrl29074.moviepage.di.MoviePageComponentProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePageFragment : Fragment() {

    private var _binding: FragmentMoviePageBinding? = null

    private val binding get() = _binding!!

    private lateinit var moviePageComponent: MoviePageComponent

    @Inject
    lateinit var viewModelFactory: MoviePageViewModelFactory

    private val viewModel: MoviePageViewModel by lazy {
        viewModelFactory.create(MoviePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDagger()
        setupFlows()
    }

    private fun setupDagger() {
        moviePageComponent = (requireActivity().application as MoviePageComponentProvider)
            .provideMoviePageComponent()

        moviePageComponent.inject(this)
    }

    private fun setupFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: State) {
        when (state) {
            State.Waiting -> {
                val movieId = arguments?.getInt("movieId")
                viewModel.getMovieById(movieId ?: 0)
            }

            State.Loading -> {
                binding.progressBar.isVisible = true
            }

            is State.Loaded -> {
                with(binding) {
                    progressBar.isVisible = false

                    val requestOptions = RequestOptions()
                        .placeholder(R.color.gray)
                        .error(R.drawable.error_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                    Glide
                        .with(poster.context)
                        .load(state.movie.posterUrl)
                        .apply(requestOptions)
                        .into(poster)

                    name.text = state.movie.name
                    description.text = state.movie.description
                    kpRating.text = state.movie.kpRating
                    imdbRating.text = state.movie.imdbRating
                    filmCriticsRating.text = state.movie.filmCriticsRating
                    russianFilmCriticsRating.text = state.movie.russianFilmCriticsRating
                    awaitRating.text = state.movie.awaitRating
                }
            }

            is State.Error -> {
//                binding.progressBar.isVisible = false
                // Show error state
            }
        }
    }
}