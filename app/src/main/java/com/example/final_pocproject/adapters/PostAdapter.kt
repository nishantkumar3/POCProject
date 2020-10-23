package com.example.final_pocproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_pocproject.model.PostKotlin
import com.example.final_pocproject.R
import com.example.final_pocproject.interfaces.PostCellClickListener

class PostAdapter(private val posts: List<PostKotlin>, private var postCellClickListener: PostCellClickListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.post_id)
        val title: TextView = itemView.findViewById(R.id.post_title)
        val body: TextView = itemView.findViewById(R.id.post_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row_layout_kotlin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = posts[position].id.toString()
        holder.title.text = posts[position].title
        holder.body.text = posts[position].body

        holder.itemView.setOnClickListener {
            postCellClickListener.onCellClickListener(posts[position].id)
        }

    }

    override fun getItemCount() = posts.size

}

