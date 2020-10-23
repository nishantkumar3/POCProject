package com.example.final_pocproject.interfaces

import com.example.final_pocproject.model.UserKotlin

interface UserInterfaceKotlin {
    fun handleSuccessResponse(users: List<UserKotlin>)

    fun handleFailure(t: Throwable)
}