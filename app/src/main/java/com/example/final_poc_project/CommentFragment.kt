package com.example.final_poc_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_poc_project.activities.PostActivity
import com.example.final_poc_project.adapters.CommentAdapter
import com.example.final_poc_project.api.CommentApi
import com.example.final_poc_project.interfaces.CommentInterface
import com.example.final_poc_project.model.Comment


class CommentFragment : Fragment(), CommentInterface {

    lateinit var sessionManager: SessionManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_comment, container, false)
        recyclerView = view.findViewById(R.id.commentRecyclerView)

        sessionManager = SessionManager(activity!!.applicationContext)
        sessionManager.checkLogin()

        val postId = sessionManager.getPostDetails()

        val commentUtilityKotlin = CommentApi(this)
        commentUtilityKotlin.getComments(postId)

        return view
    }


    override fun handleSuccessResponse(comments: List<Comment>) {
        recyclerView.layoutManager = LinearLayoutManager(activity!!.application)
        recyclerView.adapter = CommentAdapter(comments)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(activity!!.applicationContext, t.message, Toast.LENGTH_SHORT).show()
    }

}