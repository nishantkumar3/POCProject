package com.example.pocProjectKotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocProjectKotlin.R
import com.example.pocProjectKotlin.model.Comment

class CommentAdapter(private val comments: List<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.comment_id)
        val name: TextView = itemView.findViewById(R.id.comment_name)
        val email: TextView = itemView.findViewById(R.id.comment_email)
        val body: TextView = itemView.findViewById(R.id.comment_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = comments[position].id.toString()
        holder.name.text = comments[position].name
        holder.email.text = comments[position].email
        holder.body.text = comments[position].body
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}