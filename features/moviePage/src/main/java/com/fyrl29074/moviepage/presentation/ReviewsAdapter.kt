package com.fyrl29074.moviepage.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.models.domain.Review
import com.fyrl29074.moviepage.databinding.ItemReviewBinding

class ReviewsAdapter :
    PagingDataAdapter<Review, ReviewsAdapter.ReviewViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)

        review?.let {
            with(holder.binding) {
                author.text = review.author
                title.text = review.title
                type.text = review.type
                reviewText.text = review.text
            }
        }
    }

    inner class ReviewViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.type == newItem.type
                        && oldItem.text == newItem.text
                        && oldItem.author == newItem.author
            }
        }
    }
}

