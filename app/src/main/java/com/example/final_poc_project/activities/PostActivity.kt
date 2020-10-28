package com.example.final_poc_project.activities

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_kotlin)

        val extras = intent.extras ?: return
        val userId = extras.get("USER_ID")

        var postUtilityKotlin = PostApi(this)
        postUtilityKotlin.getPost(userId as Int)

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
        val intent = Intent(this@PostActivity, CommentActivity::class.java)
        intent.putExtra("POST_ID", id.toString())
        startActivity(intent)
    }

}
