package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.UserKotlin

interface UserInterfaceKotlin {
    fun handleSuccessResponse(users: List<UserKotlin>)

    fun handleFailure(t: Throwable)
}