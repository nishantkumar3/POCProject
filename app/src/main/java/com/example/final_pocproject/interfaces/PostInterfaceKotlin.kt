package com.example.final_pocproject.interfaces

import com.example.final_pocproject.model.PostKotlin

interface PostInterfaceKotlin {
    fun handleSuccessResponse(posts: List<PostKotlin>)

    fun handleFailure(t: Throwable)
}