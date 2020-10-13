package com.example.final_pocproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import com.example.final_pocproject.Model.CommentList;
import com.example.final_pocproject.R;

import java.util.List;

public class CommentListViewAdapter extends BaseAdapter {

    public static List<CommentList> comments;   //list of comments for a post
    public Context context;
    public LayoutInflater layoutInflater;

    public CommentListViewAdapter(Context context,List<CommentList> comments){
        this.context = context;
        this.comments=comments;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        protected TextView commentIdText;
        protected TextView commentNameText;
        protected TextView commentEmailText;
        protected TextView commentBodyText;

        public ViewHolder(View view){
            commentIdText = view.findViewById(R.id.comment_id);
            commentNameText = view.findViewById(R.id.comment_name);
            commentEmailText = view.findViewById(R.id.comment_email);
            commentBodyText = view.findViewById(R.id.comment_body);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.comment_row_layout,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.commentIdText.setText(String.valueOf(comments.get(position).getId()));
        holder.commentNameText.setText(comments.get(position).getName());
        holder.commentEmailText.setText(comments.get(position).getEmail());
        holder.commentBodyText.setText(comments.get(position).getBody());

        return view;
    }
}
