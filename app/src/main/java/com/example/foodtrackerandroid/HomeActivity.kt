package com.example.foodtrackerandroid

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodtrackerandroid.adapter.MainCategoryAdapter
import com.example.foodtrackerandroid.adapter.SubCategoryAdapter
import com.example.foodtrackerandroid.database.RecipeDatabase
import com.example.foodtrackerandroid.entities.CategoryItems
import com.example.foodtrackerandroid.entities.MealsItems
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()
        mainCategoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedSubitem)

    }

    private val onClicked =  object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onClickedSubitem =  object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }



    private fun getDataFromDb(){
        launch {
            this.let {

                    var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                    arrMainCategory = cat as ArrayList<CategoryItems>
                    arrMainCategory.reverse()

                    getMealDataFromDb(arrMainCategory[0].strcategory)
                    mainCategoryAdapter.setData(arrMainCategory)
                    rv_main_category.layoutManager =
                            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                    rv_main_category.adapter = mainCategoryAdapter

            }
        }
    }

    private fun getMealDataFromDb(categoryName:String){
            tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)
                rv_sub_category.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_sub_category.adapter = subCategoryAdapter
                //rv_sub_category!!.adapter.notifyDataSetChange()
            }
        }
    }
}

