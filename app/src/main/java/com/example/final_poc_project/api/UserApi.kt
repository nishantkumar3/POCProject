package com.example.final_poc_project.api

import com.example.final_poc_project.retrofit.ApiInterface
import com.example.final_poc_project.retrofit.RetrofitClientKotlin
import com.example.final_poc_project.interfaces.UserInterface
import com.example.final_poc_project.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserApi(private val userInterface: UserInterface) {
    private val apiInterface: ApiInterface = RetrofitClientKotlin.getRetrofit().create(ApiInterface::class.java)

    fun getUser() {
        val call: Call<List<User>> = apiInterface.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (!response.isSuccessful)
                    return
                userInterface.handleSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                userInterface.handleFailure(t)
            }
        })
    }
}