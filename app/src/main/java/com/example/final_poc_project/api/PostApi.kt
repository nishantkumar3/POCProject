package com.example.final_poc_project.api


import com.example.final_poc_project.retrofit.ApiInterface
import com.example.final_poc_project.retrofit.RetrofitClientKotlin
import com.example.final_poc_project.interfaces.PostInterface
import com.example.final_poc_project.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApi(private val postInterface: PostInterface) {
    private val apiInterface: ApiInterface = RetrofitClientKotlin.getRetrofit().create(ApiInterface::class.java)

    fun getPost(userId: Int) {
        val call: Call<List<Post>> = apiInterface.getPosts(userId)
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful)
                    return
                postInterface.handleSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                postInterface.handleFailure(t)
            }
        })
    }
}