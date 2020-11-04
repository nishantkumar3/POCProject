package com.example.final_poc_project.interfaces

import com.example.final_poc_project.model.User


interface UserInterface {
    fun handleSuccessResponse(users: List<User>)

    fun handleFailure(t: Throwable)

    fun responseNotStressful(responseCode : Int)
}