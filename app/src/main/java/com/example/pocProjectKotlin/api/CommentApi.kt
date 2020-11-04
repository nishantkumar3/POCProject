package com.example.pocProjectKotlin.api

import com.example.pocProjectKotlin.retrofit.ApiInterface
import com.example.pocProjectKotlin.retrofit.RetrofitClientKotlin
import com.example.pocProjectKotlin.interfaces.CommentInterface
import com.example.pocProjectKotlin.model.Comment
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