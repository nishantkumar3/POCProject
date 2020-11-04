package com.example.pocProjectKotlin.api


import com.example.pocProjectKotlin.retrofit.ApiInterface
import com.example.pocProjectKotlin.retrofit.RetrofitClientKotlin
import com.example.pocProjectKotlin.interfaces.PostInterface
import com.example.pocProjectKotlin.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApi(private val postInterface: PostInterface) {
    private val apiInterface: ApiInterface = RetrofitClientKotlin.getRetrofit().create(ApiInterface::class.java)

    fun getPost(userId: Int) {
        val call: Call<List<Post>> = apiInterface.getPosts(userId)
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    postInterface.responseNotStressful(response.code())
                    return
                }

                response.body()?.let { postInterface.handleSuccessResponse(it) }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                postInterface.handleFailure(t)
            }
        })
    }
}