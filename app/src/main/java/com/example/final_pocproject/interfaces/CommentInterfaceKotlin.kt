package com.example.final_pocproject.interfaces

import com.example.final_pocproject.model.CommentKotlin

interface CommentInterfaceKotlin {
    fun handleSuccessResponse(comments: List<CommentKotlin>)

    fun handleFailure(t: Throwable)
}