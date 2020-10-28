package com.example.final_poc_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.final_poc_project.*
import com.example.final_poc_project.interfaces.UserInterface
import com.example.final_poc_project.model.User
import com.example.final_poc_project.api.UserApi
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity(), UserInterface {

    lateinit var loginButton: Button
    lateinit var inputEmail: TextInputLayout
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var userApi: UserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)

        loginButton = findViewById(R.id.loginButton)
        inputEmail = findViewById(R.id.email)
        userApi = UserApi(this)

        loginButton.setOnClickListener(View.OnClickListener {

            val emailId: String = inputEmail.editText?.text.toString().trim()

            if (validEmail(emailId))
                userApi.getUser()
        })

    }

    private fun validEmail(emailId: String): Boolean {

        var isValid: Boolean = false
        if (emailId.isEmpty()) {
            inputEmail.error = "Email field can't be empty"
        } else if (emailId.matches(emailPattern.toRegex())) {
            inputEmail.error = null
            isValid = true
        } else {
            inputEmail.error = "Invalid email address"
        }
        return isValid
    }

    override fun handleSuccessResponse(users: List<User>) {

        val emailEntered: String = inputEmail.editText?.text.toString()

        var userId: Int = 0
        for (i: Int in users.indices)
            if (users[i]?.email.equals(emailEntered, true)) {
                userId = users[i].id
            }

        if (userId != 0) {
            val intent = Intent(this@MainActivity, PostActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "Email id not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
}

