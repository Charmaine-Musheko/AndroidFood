package com.example.foodtrackerandroid.database


import com.example.foodtrackerandroid.dao.ReviewDao

class ReviewDatabase private constructor() {

    var reviewDao = ReviewDao()
    private set
    companion object {
        @Volatile private var instance: ReviewDatabase? = null
        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: ReviewDatabase().also { instance = it }
            }
    }
}