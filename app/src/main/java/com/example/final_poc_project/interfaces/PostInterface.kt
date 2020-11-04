package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.Post

interface PostInterface {
    fun handleSuccessResponse(posts: List<Post>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}