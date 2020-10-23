package com.example.final_poc_project.utility


import com.example.final_poc_project.api.ApiInterfaceKotlin
import com.example.final_poc_project.api.RetrofitClientKotlin
import com.example.final_poc_project.interfaces.PostInterfaceKotlin
import com.example.final_poc_project.model.PostKotlin
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