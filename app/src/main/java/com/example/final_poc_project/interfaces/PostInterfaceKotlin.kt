package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.PostKotlin

interface PostInterfaceKotlin {
    fun handleSuccessResponse(posts: List<PostKotlin>)

    fun handleFailure(t: Throwable)
}