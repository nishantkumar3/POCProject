package com.example.pocProjectKotlin.interfaces

import com.example.pocProjectKotlin.model.User


interface UserInterface {
    fun handleSuccessResponse(users: List<User>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}