package com.example.foodtrackerandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.foodtrackerandroid.entities.MealResponse
import com.example.foodtrackerandroid.entities.ReviewData
import com.example.foodtrackerandroid.interfaces.GetDataService
import com.example.foodtrackerandroid.retrofitclient.RetrofitClientInstance
import com.example.foodtrackerandroid.viewmodel.InjectorUtils
import com.example.foodtrackerandroid.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class DetailActivity : BaseActivity() {

    var youtubeLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getStringExtra("id")

        getSpecificItem(id!!)

        imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        btnYoutube.setOnClickListener {
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        initializeUi()

    }

    private fun initializeUi(){
        val factory = InjectorUtils.provideReviewViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(ReviewViewModel::class.java)
        viewModel.getReviews().observe(this, Observer { reviews ->val stringBuilder = StringBuilder()
            reviews.forEach{review -> stringBuilder.append("$review\n\n")
            }
            textView_reviews.text = stringBuilder.toString()
        })
        button_add_review.setOnClickListener{
            val review = ReviewData(editText_review.text.toString(), editText_author.text.toString())
            viewModel.addReview(review)
            editText_review.setText("")
            editText_author.setText("")
        }
    }


    fun getSpecificItem(id: String) {
       val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onResponse(
                    call: Call<MealResponse>,
                    response: Response<MealResponse>
            ) {

                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(imgItem)

                tvCategory.text = response.body()!!.mealsEntity[0].strmeal


                var ingredient = "${response.body()!!.mealsEntity[0].stringredient1}      ${response.body()!!.mealsEntity[0].strmeasure1}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient2}      ${response.body()!!.mealsEntity[0].strmeasure2}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient3}      ${response.body()!!.mealsEntity[0].strmeasure3}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient4}      ${response.body()!!.mealsEntity[0].strmeasure4}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient5}      ${response.body()!!.mealsEntity[0].strmeasure5}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient6}      ${response.body()!!.mealsEntity[0].strmeasure6}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient7}      ${response.body()!!.mealsEntity[0].strmeasure7}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient8}      ${response.body()!!.mealsEntity[0].strmeasure8}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient9}      ${response.body()!!.mealsEntity[0].strmeasure9}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient10}      ${response.body()!!.mealsEntity[0].strmeasure10}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient11}      ${response.body()!!.mealsEntity[0].strmeasure11}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient12}      ${response.body()!!.mealsEntity[0].strmeasure12}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient13}      ${response.body()!!.mealsEntity[0].strmeasure13}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient14}      ${response.body()!!.mealsEntity[0].strmeasure14}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient15}      ${response.body()!!.mealsEntity[0].strmeasure15}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient16}      ${response.body()!!.mealsEntity[0].strmeasure16}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient17}      ${response.body()!!.mealsEntity[0].strmeasure17}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient18}      ${response.body()!!.mealsEntity[0].strmeasure18}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient19}      ${response.body()!!.mealsEntity[0].strmeasure19}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient20}      ${response.body()!!.mealsEntity[0].strmeasure20}\n"

                tvIngredients.text = ingredient
                tvInstructions.text = response.body()!!.mealsEntity[0].strinstructions

                if (response.body()!!.mealsEntity[0].strsource != null){
                    youtubeLink = response.body()!!.mealsEntity[0].strsource
                }else{
                    btnYoutube.visibility = View.GONE
                }
            }

        })
    }


}

