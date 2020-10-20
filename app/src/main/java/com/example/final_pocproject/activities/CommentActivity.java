package com.example.final_pocproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.final_pocproject.interfaces.CommentInterface;
import com.example.final_pocproject.utility.CommentUtility;
import com.example.final_pocproject.model.Comments;
import com.example.final_pocproject.adapters.CommentListViewAdapter;
import com.example.final_pocproject.R;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements CommentInterface {


    private ListView commentListView;

    CommentListViewAdapter adapter;
    CommentUtility commentUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        commentListView = findViewById(R.id.commentListView);

        Intent intent = getIntent();
        int postId = intent.getIntExtra("postId", 0);

        commentUtility = new CommentUtility(this);
        commentUtility.getComments(postId);
    }

    @Override
    public void handleSuccessResponse(List<Comments> comments) {

        if (comments == null) {
            Toast.makeText(CommentActivity.this, "Comments could not be loaded", Toast.LENGTH_SHORT).show();
        }

        adapter = new CommentListViewAdapter(getApplicationContext(), comments);
        commentListView.setAdapter(adapter);
    }

    @Override
    public void handleFailure(Throwable t) {
        Toast.makeText(CommentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}