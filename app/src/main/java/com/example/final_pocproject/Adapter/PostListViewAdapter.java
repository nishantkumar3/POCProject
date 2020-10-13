package com.example.final_pocproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.final_pocproject.Model.Post;
import com.example.final_pocproject.R;

import java.util.List;

public class PostListViewAdapter extends BaseAdapter {

    public static List<Post> posts;   //list of posts for a user
    public Context context;
    public LayoutInflater layoutInflater;

    public PostListViewAdapter(Context context,List<Post> posts){
        this.context = context;
        this.posts=posts;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        protected TextView postIdText;
        protected TextView postTitleText;
        protected TextView postBodyText;

        public ViewHolder(View view){
            postIdText = view.findViewById(R.id.post_id);
            postTitleText = view.findViewById(R.id.post_title);
            postBodyText = view.findViewById(R.id.post_body);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        PostListViewAdapter.ViewHolder holder = null;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.post_single_row_layout,parent,false);
            holder = new PostListViewAdapter.ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (PostListViewAdapter.ViewHolder) view.getTag();
        }

        holder.postIdText.setText(String.valueOf(posts.get(position).getId()));
        holder.postTitleText.setText(posts.get(position).getTitle());
        holder.postBodyText.setText(posts.get(position).getBody());

        return view;
    }

}
