package com.example.foodtrackerandroid.entities

data class ReviewData (val reviewText:String,
        val author:String){

    override fun toString(): String{
        return "$reviewText - $author"
    }
}