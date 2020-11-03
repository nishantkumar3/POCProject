package com.example.final_poc_project.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_poc_project.adapters.CommentAdapter
import com.example.final_poc_project.R
import com.example.final_poc_project.SessionManager
import com.example.final_poc_project.interfaces.CommentInterface
import com.example.final_poc_project.model.Comment
import com.example.final_poc_project.api.CommentApi

class CommentActivity : AppCompatActivity(), CommentInterface {

    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        sessionManager = SessionManager(applicationContext)
        sessionManager.checkLogin()
      //  sessionManager.changeScreenStatus(2)

        val postId = sessionManager.getPostDetails()

        val commentUtilityKotlin = CommentApi(this)
        commentUtilityKotlin.getComments(postId)
    }

    override fun onBackPressed() {
        val intent = Intent(this, PostActivity::class.java)
     //   sessionManager.changeScreenStatus(1)
        startActivity(intent)
        finish()
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

    override fun handleSuccessResponse(comments: List<Comment>) {
        val recyclerView: RecyclerView = findViewById(R.id.commentRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter(comments)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}