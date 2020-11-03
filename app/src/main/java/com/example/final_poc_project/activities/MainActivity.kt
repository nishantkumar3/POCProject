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
import com.example.final_poc_project.sessionmanagement.SessionManager
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity(), UserInterface {

    lateinit var loginButton: Button
    lateinit var inputEmail: TextInputLayout
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var userApi: UserApi
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(applicationContext)
        if (sessionManager.isLoggedIn()) {
            val intent: Intent = Intent(this, DashboardActivity::class.java)
            finish()
            startActivity(intent)
        }

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
            inputEmail.error = getString(R.string.emailErrorMessage)
        } else if (emailId.matches(emailPattern.toRegex())) {
            inputEmail.error = null
            isValid = true
        } else {
            inputEmail.error = getString(R.string.invalidEmail)
        }
        return isValid
    }

    override fun handleSuccessResponse(users: List<User>) {
        val emailEntered: String = inputEmail.editText?.text.toString()
        var userId: Int = 0
        for (i: Int in users.indices)
            if (users[i].email.equals(emailEntered, true)) {
                userId = users[i].id
            }

        if (userId != 0) {
            sessionManager.createLoginSession(emailEntered, userId)
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            finish()
            startActivity(intent)
        } else {
            inputEmail.error = getString(R.string.emailNotFound)
        }
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
}

