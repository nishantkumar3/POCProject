package com.example.final_poc_project.utility

import com.example.final_poc_project.api.ApiInterfaceKotlin
import com.example.final_poc_project.api.RetrofitClientKotlin
import com.example.final_poc_project.interfaces.UserInterfaceKotlin
import com.example.final_poc_project.model.UserKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserUtilityKotlin(private val userInterfaceKotlin: UserInterfaceKotlin) {
    private val apiInterfaceKotlin: ApiInterfaceKotlin = RetrofitClientKotlin.getRetrofit().create(ApiInterfaceKotlin::class.java)

    fun getUser() {
        val call: Call<List<UserKotlin>> = apiInterfaceKotlin.getUsers()
        call.enqueue(object : Callback<List<UserKotlin>> {
            override fun onResponse(call: Call<List<UserKotlin>>, response: Response<List<UserKotlin>>) {
                if (!response.isSuccessful)
                    return
                userInterfaceKotlin.handleSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserKotlin>>, t: Throwable) {
                userInterfaceKotlin.handleFailure(t)
            }
        })
    }
}