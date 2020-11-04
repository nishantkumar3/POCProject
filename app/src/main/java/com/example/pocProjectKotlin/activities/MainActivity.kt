package com.example.pocProjectKotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.pocProjectKotlin.*
import com.example.pocProjectKotlin.interfaces.UserInterface
import com.example.pocProjectKotlin.model.User
import com.example.pocProjectKotlin.api.UserApi
import com.example.pocProjectKotlin.sessionmanagement.SessionManager
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity(), UserInterface {

    private lateinit var loginButton: Button
    private lateinit var inputEmail: TextInputLayout
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var userApi: UserApi
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(applicationContext)
        if (sessionManager.isLoggedIn()) {
            val intent = Intent(this, DashboardActivity::class.java)
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
        var isValid = false
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

    override fun responseNotStressful(responseCode: Int) {
        Toast.makeText(this@MainActivity, """Code : $responseCode""", Toast.LENGTH_SHORT).show()
    }
}

