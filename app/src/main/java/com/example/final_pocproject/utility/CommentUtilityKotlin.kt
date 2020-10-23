package com.example.final_pocproject.utility

import com.example.final_pocproject.api.ApiInterfaceKotlin
import com.example.final_pocproject.api.RetrofitClientKotlin
import com.example.final_pocproject.interfaces.CommentInterfaceKotlin
import com.example.final_pocproject.model.CommentKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentUtilityKotlin(private val commentInterfaceKotlin: CommentInterfaceKotlin) {
    private val apiInterfaceKotlin: ApiInterfaceKotlin = RetrofitClientKotlin.getRetrofit().create(ApiInterfaceKotlin::class.java)

    fun getComments(postId: Int) {
        val call: Call<List<CommentKotlin>> = apiInterfaceKotlin.getComments(postId)
        call.enqueue(object : Callback<List<CommentKotlin>> {
            override fun onResponse(call: Call<List<CommentKotlin>>, response: Response<List<CommentKotlin>>) {
                if (!response.isSuccessful)
                    return
                commentInterfaceKotlin.handleSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<CommentKotlin>>, t: Throwable) {
                commentInterfaceKotlin.handleFailure(t)
            }
        })
    }
}