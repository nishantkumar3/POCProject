package com.example.pocProjectKotlin.interfaces

import com.example.pocProjectKotlin.model.Post

interface PostInterface {
    fun handleSuccessResponse(posts: List<Post>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}