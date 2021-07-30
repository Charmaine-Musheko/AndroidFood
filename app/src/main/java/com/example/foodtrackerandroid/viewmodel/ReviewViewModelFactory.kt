package com.example.foodtrackerandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.foodtrackerandroid.database.ReviewRepository

class ReviewViewModelFactory(private val reviewRepository: ReviewRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewViewModel(
            reviewRepository
        ) as T
    }
}