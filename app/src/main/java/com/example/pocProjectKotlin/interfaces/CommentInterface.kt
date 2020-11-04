package com.example.pocProjectKotlin.interfaces

import com.example.pocProjectKotlin.model.Comment

interface CommentInterface {
    fun handleSuccessResponse(comments: List<Comment>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}