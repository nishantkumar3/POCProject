package com.example.pocProjectKotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.pocProjectKotlin.fragments.PostFragment
import com.example.pocProjectKotlin.R
import com.example.pocProjectKotlin.sessionmanagement.SessionManager

class DashboardActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_layout)

        sessionManager = SessionManager(applicationContext)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = PostFragment()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        sessionManager.logoutUser()
        return super.onOptionsItemSelected(item)
    }
}