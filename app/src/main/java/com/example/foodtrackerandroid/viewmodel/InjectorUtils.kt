package com.example.foodtrackerandroid.viewmodel


import com.example.foodtrackerandroid.database.ReviewDatabase
import com.example.foodtrackerandroid.database.ReviewRepository
import com.example.foodtrackerandroid.viewmodel.ReviewViewModelFactory

object InjectorUtils {
    fun provideReviewViewModelFactory(): ReviewViewModelFactory {
        val reviewRepository = ReviewRepository.getInstance(ReviewDatabase.getInstance().reviewDao)
        return ReviewViewModelFactory(
            reviewRepository
        )
    }
}