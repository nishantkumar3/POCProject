package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.Comment

interface CommentInterface {
    fun handleSuccessResponse(comments: List<Comment>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}