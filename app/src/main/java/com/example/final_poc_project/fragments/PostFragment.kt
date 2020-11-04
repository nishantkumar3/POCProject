package com.example.final_poc_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_poc_project.R
import com.example.final_poc_project.sessionmanagement.SessionManager
import com.example.final_poc_project.adapters.PostAdapter
import com.example.final_poc_project.api.PostApi
import com.example.final_poc_project.clickListener.PostCellClickListener
import com.example.final_poc_project.interfaces.PostInterface
import com.example.final_poc_project.model.Post


class PostFragment : Fragment(), PostInterface, PostCellClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.postRecyclerView)

        sessionManager = SessionManager(activity!!.applicationContext)
        sessionManager.checkLogin()

        val user: HashMap<String, String> = sessionManager.getUserDetails()
        val userId = user[SessionManager.KEY_USERID]!!

        val postUtilityKotlin = PostApi(this)
        postUtilityKotlin.getPost(Integer.parseInt(userId))
        return view
    }

    override fun handleSuccessResponse(posts: List<Post>) {
        recyclerView.layoutManager = LinearLayoutManager(activity!!.application)
        recyclerView.adapter = PostAdapter(posts, this)
    }

    override fun handleFailure(t: Throwable) {
        Toast.makeText(activity!!.applicationContext, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun responseNotStressful(responseCode: Int) {
        Toast.makeText(activity?.applicationContext, """Code : $responseCode""", Toast.LENGTH_SHORT).show()

    }

    override fun onCellClickListener(id: Any) {
        sessionManager.createPostSession(id as Int)
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val fragment = CommentFragment()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}