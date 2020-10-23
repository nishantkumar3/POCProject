package com.example.final_pocproject.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pocproject.*
import com.example.final_pocproject.adapters.PostAdapter
import com.example.final_pocproject.interfaces.PostCellClickListener
import com.example.final_pocproject.interfaces.PostInterfaceKotlin
import com.example.final_pocproject.model.PostKotlin
import com.example.final_pocproject.utility.PostUtilityKotlin


class PostActivityKotlin : AppCompatActivity(), PostInterfaceKotlin, PostCellClickListener {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_kotlin)

        val extras = intent.extras ?: return
        val userId = extras.get("USER_ID")

        var postUtilityKotlin = PostUtilityKotlin(this)
        postUtilityKotlin.getPost(userId as Int)

    }

    override fun handleSuccessResponse(posts: List<PostKotlin>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PostActivityKotlin)
        recyclerView.adapter = PostAdapter(posts, this)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(this@PostActivityKotlin, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCellClickListener(id: Any) {
        val intent = Intent(this@PostActivityKotlin, CommentActivityKotlin::class.java)
        intent.putExtra("POST_ID", id.toString())
        startActivity(intent)
    }

}
