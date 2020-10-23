package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.CommentKotlin

interface CommentInterfaceKotlin {
    fun handleSuccessResponse(comments: List<CommentKotlin>)

    fun handleFailure(t: Throwable)
}