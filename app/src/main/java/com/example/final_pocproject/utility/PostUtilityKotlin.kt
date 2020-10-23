package com.example.final_pocproject.utility


import com.example.final_pocproject.api.ApiInterfaceKotlin
import com.example.final_pocproject.api.RetrofitClientKotlin
import com.example.final_pocproject.interfaces.PostInterfaceKotlin
import com.example.final_pocproject.model.PostKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostUtilityKotlin(private val postInterfaceKotlin: PostInterfaceKotlin) {
    private val apiInterfaceKotlin: ApiInterfaceKotlin = RetrofitClientKotlin.getRetrofit().create(ApiInterfaceKotlin::class.java)

    fun getPost(userId: Int) {
        val call: Call<List<PostKotlin>> = apiInterfaceKotlin.getPosts(userId)
        call.enqueue(object : Callback<List<PostKotlin>> {
            override fun onResponse(call: Call<List<PostKotlin>>, response: Response<List<PostKotlin>>) {
                if (!response.isSuccessful)
                    return
                postInterfaceKotlin.handleSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<PostKotlin>>, t: Throwable) {
                postInterfaceKotlin.handleFailure(t)
            }
        })
    }
}