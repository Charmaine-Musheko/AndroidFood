package com.example.foodtrackerandroid.database


import com.example.foodtrackerandroid.dao.ReviewDao
import com.example.foodtrackerandroid.entities.ReviewData

class ReviewRepository private constructor(private val reviewDao: ReviewDao) {
    fun addReview(review: ReviewData){
        reviewDao.addReview(review)
    }
        fun getReviews() = reviewDao.getReviews()

    companion object{
        @Volatile private var instance: ReviewRepository? = null

        fun getInstance(reviewDao: ReviewDao) =
            instance ?: synchronized(this){
                instance ?: ReviewRepository(reviewDao).also { instance = it }
            }
    }
}