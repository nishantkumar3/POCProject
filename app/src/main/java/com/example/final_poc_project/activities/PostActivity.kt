package com.example.final_poc_project.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_poc_project.*
import com.example.final_poc_project.adapters.PostAdapter
import com.example.final_poc_project.clickListener.PostCellClickListener
import com.example.final_poc_project.interfaces.PostInterface
import com.example.final_poc_project.model.Post
import com.example.final_poc_project.api.PostApi


class PostActivity : AppCompatActivity(), PostInterface, PostCellClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        sessionManager = SessionManager(applicationContext)

        sessionManager.checkLogin()
        sessionManager.changeScreenStatus(1)

        val user: HashMap<String,String> = sessionManager.getUserDetails()
        val userId = user[SessionManager.KEY_USERID]!!

        val postUtilityKotlin = PostApi(this)
        postUtilityKotlin.getPost(Integer.parseInt(userId))

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

    override fun handleSuccessResponse(posts: List<Post>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PostActivity)
        recyclerView.adapter = PostAdapter(posts, this)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this@PostActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCellClickListener(id: Any) {
        sessionManager.createPostSession(id as Int)
        val intent = Intent(this@PostActivity, CommentActivity::class.java)
       //intent.putExtra("POST_ID", id as Int)
        startActivity(intent)
    }

}
