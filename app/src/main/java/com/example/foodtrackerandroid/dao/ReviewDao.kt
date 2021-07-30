package com.example.foodtrackerandroid.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodtrackerandroid.entities.ReviewData

class ReviewDao {
    private val reviewList = mutableListOf<ReviewData>()
    private val reviews = MutableLiveData< List<ReviewData>>()

    init {
        reviews.value = reviewList
    }
    fun addReview(review: ReviewData) {
        reviewList.add(review)
        reviews.value = reviewList
    }
    fun getReviews() = reviews as LiveData<List<ReviewData>>
}