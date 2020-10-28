package com.example.final_poc_project.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_poc_project.adapters.CommentAdapter
import com.example.final_poc_project.R
import com.example.final_poc_project.interfaces.CommentInterface
import com.example.final_poc_project.model.Comment
import com.example.final_poc_project.api.CommentApi

class CommentActivity : AppCompatActivity(), CommentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_kotlin)

        val extras = intent.extras ?: return
        val postId = extras.get("POST_ID")

        val commentUtilityKotlin = CommentApi(this)
        commentUtilityKotlin.getComments(Integer.parseInt(postId.toString()))
    }

    override fun handleSuccessResponse(comments: List<Comment>) {
        var recyclerView: RecyclerView = findViewById(R.id.commentRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter(comments)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}