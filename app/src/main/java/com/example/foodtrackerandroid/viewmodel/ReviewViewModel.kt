package com.example.foodtrackerandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodtrackerandroid.database.ReviewRepository
import com.example.foodtrackerandroid.entities.ReviewData

class ReviewViewModel(private val reviewRepository: ReviewRepository): ViewModel() {
fun getReviews() = reviewRepository.getReviews()
    fun addReview(review: ReviewData) = reviewRepository.addReview(review)
}