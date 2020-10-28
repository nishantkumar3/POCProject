package com.example.final_poc_project.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientKotlin {

    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
        }

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


}