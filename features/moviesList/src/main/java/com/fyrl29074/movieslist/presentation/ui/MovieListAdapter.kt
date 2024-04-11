package com.fyrl29074.movieslist.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fyrl29074.movieslist.R
import com.fyrl29074.movieslist.databinding.ItemMovieBinding
import com.fyrl29074.movieslist.presentation.model.MovieVO

class MovieListAdapter :
    PagingDataAdapter<MovieVO, MovieListAdapter.MovieViewHolder>(MOVIES_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            with(holder.binding) {
                title.text = movie.name
                country.text = movie.countries
                year.text = movie.year
                ageRating.text = movie.ageRating

                val requestOptions = RequestOptions()
                    .placeholder(R.color.gray)
                    .error(R.drawable.error_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide
                    .with(previewImage.context)
                    .load(movie.posterPreviewUrl)
                    .apply(requestOptions)
                    .into(previewImage)
            }
        }
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val MOVIES_DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieVO>() {
            override fun areItemsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean =
                oldItem == newItem
        }
    }
}