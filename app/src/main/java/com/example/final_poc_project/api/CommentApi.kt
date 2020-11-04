package com.example.final_poc_project.api

import com.example.final_poc_project.retrofit.ApiInterface
import com.example.final_poc_project.retrofit.RetrofitClientKotlin
import com.example.final_poc_project.interfaces.CommentInterface
import com.example.final_poc_project.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentApi(private val commentInterface: CommentInterface) {
    private val apiInterface: ApiInterface = RetrofitClientKotlin.getRetrofit().create(ApiInterface::class.java)

    fun getComments(postId: Int) {
        val call: Call<List<Comment>> = apiInterface.getComments(postId)
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (!response.isSuccessful) {
                    commentInterface.responseNotStressful(response.code())
                    return
                }

                response.body()?.let { commentInterface.handleSuccessResponse(it) }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                commentInterface.handleFailure(t)
            }
        })
    }
}