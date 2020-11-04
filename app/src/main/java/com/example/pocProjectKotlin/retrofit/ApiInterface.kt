package com.example.pocProjectKotlin.retrofit

import com.example.pocProjectKotlin.model.Comment
import com.example.pocProjectKotlin.model.Post
import com.example.pocProjectKotlin.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}/posts")
    fun getPosts(@Path("id") userId: Int): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<Comment>>
}