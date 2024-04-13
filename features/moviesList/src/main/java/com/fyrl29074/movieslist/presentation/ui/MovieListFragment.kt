package com.fyrl29074.movieslist.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState.Loading
import androidx.paging.PagingData
import com.fyrl29074.movieslist.databinding.FragmentMovieListBinding
import com.fyrl29074.movieslist.di.MoviesListComponent
import com.fyrl29074.movieslist.di.MoviesListComponentProvider
import com.fyrl29074.movieslist.presentation.viewModel.MovieListViewModel
import com.fyrl29074.movieslist.presentation.viewModel.MovieListViewModelFactory
import com.fyrl29074.navigation.NavScreens
import com.fyrl29074.navigation.Navigation
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment() {

    // TODO: Лучше добавить проверку на веденные фильтры
    // TODO: + показывать ошибку, если сервер сказал неверные фильтры

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesListComponent: MoviesListComponent

    private val adapter = MovieListAdapter { id -> onClick(id) }

    private val searchFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

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
        setupFlows()
    }

    private fun setupUI() {
        binding.recyclerViewMoviesList.adapter = adapter

        binding.applyFilters.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(PagingData.empty())

                viewModel.applyFilters(
                    name = binding.search.text.toString().takeIf { name ->
                        name.isNotBlank()
                    },
                    fromYear = binding.fromYear.text.toString().toIntOrNull(),
                    toYear = binding.toYear.text.toString().toIntOrNull(),
                    country = binding.countryPicker.text.toString().takeIf { country ->
                        country.isNotBlank()
                    },
                    ageRating = binding.ageRatingPicker.text.toString().toIntOrNull()
                )
            }
        }

        binding.search.addTextChangedListener(
            afterTextChanged = { editable ->
                viewLifecycleOwner.lifecycleScope.launch {
                    adapter.submitData(PagingData.empty())

                    viewLifecycleOwner.lifecycleScope.launch {
                        searchFlow.emit(editable.toString())
                    }
                }
            }
        )
    }

    private fun setUpDagger() {
        moviesListComponent = (requireActivity().application as MoviesListComponentProvider)
            .provideMoviesListComponentProvider()

        moviesListComponent.inject(this)
    }

    @OptIn(FlowPreview::class)
    private fun setupFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.downloadingProgressBar.isVisible = it.source.append is Loading
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagedMovies.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchFlow
                    .debounce(1000)
                    .collectLatest { query ->
                        adapter.submitData(PagingData.empty())
                        viewModel.applyFilters(name = query)
                    }
            }
        }
    }

    private fun onClick(id: Int) {
        (requireActivity() as Navigation).navigateToMoviePage(
            fragment = this,
            screen = NavScreens.MoviePage,
            id = id
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}