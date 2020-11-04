package com.example.pocProjectKotlin.api

import com.example.pocProjectKotlin.retrofit.ApiInterface
import com.example.pocProjectKotlin.retrofit.RetrofitClientKotlin
import com.example.pocProjectKotlin.interfaces.UserInterface
import com.example.pocProjectKotlin.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserApi(private val userInterface: UserInterface) {
    private val apiInterface: ApiInterface = RetrofitClientKotlin.getRetrofit().create(ApiInterface::class.java)

    fun getUser() {
        val call: Call<List<User>> = apiInterface.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (!response.isSuccessful) {
                    userInterface.responseNotStressful(response.code())
                    return
                }
                response.body()?.let { userInterface.handleSuccessResponse(it) }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                userInterface.handleFailure(t)
            }
        })
    }
}