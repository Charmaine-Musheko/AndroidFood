package com.example.foodtrackerandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodtrackerandroid.dao.RecipeDao
import com.example.foodtrackerandroid.entities.*
import com.example.foodtrackerandroid.entities.converter.CategoryListConverter
import com.example.foodtrackerandroid.entities.converter.MealListConverter

@Database(entities = [Recipes::class, CategoryItems::class, Category::class, Meal::class, MealsItems::class],version = 1,exportSchema = false)
@TypeConverters(
        CategoryListConverter::class,
        MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}