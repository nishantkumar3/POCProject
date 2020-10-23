package com.example.final_poc_project.api

import com.example.final_poc_project.model.CommentKotlin
import com.example.final_poc_project.model.PostKotlin
import com.example.final_poc_project.model.UserKotlin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceKotlin {

    @GET("users")
    fun getUsers(): Call<List<UserKotlin>>

    @GET("users/{id}/posts")
    fun getPosts(@Path("id") userId: Int): Call<List<PostKotlin>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<CommentKotlin>>
}