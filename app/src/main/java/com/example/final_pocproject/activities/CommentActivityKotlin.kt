package com.example.final_pocproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pocproject.adapters.CommentAdapter
import com.example.final_pocproject.R
import com.example.final_pocproject.interfaces.CommentInterfaceKotlin
import com.example.final_pocproject.model.CommentKotlin
import com.example.final_pocproject.utility.CommentUtilityKotlin

class CommentActivityKotlin : AppCompatActivity(), CommentInterfaceKotlin {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_kotlin)

        val extras = intent.extras ?: return
        val postId = extras.get("POST_ID")

        val commentUtilityKotlin = CommentUtilityKotlin(this)
        commentUtilityKotlin.getComments(Integer.parseInt(postId.toString()))
    }

    override fun handleSuccessResponse(comments: List<CommentKotlin>) {
        var recyclerView: RecyclerView = findViewById(R.id.commentRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter(comments)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}