package com.example.final_pocproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_pocproject.adapters.CommentListViewAdapter;
import com.example.final_pocproject.api.ApiInterface;
import com.example.final_pocproject.interfaces.PostInterface;
import com.example.final_pocproject.model.Post;
import com.example.final_pocproject.adapters.PostListViewAdapter;
import com.example.final_pocproject.R;
import com.example.final_pocproject.api.RetrofitClient;
import com.example.final_pocproject.utility.PostUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity implements PostInterface {

    ApiInterface apiInterface;
    PostUtility postUtility;
    private List<Post> postList;
    private ListView postListView;
    PostListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0);

        postListView = findViewById(R.id.listView);

        postUtility = new PostUtility(this);
        postUtility.getPosts(userId);


        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewId = view.findViewById(R.id.post_id);
                int postId = Integer.parseInt(String.valueOf(textViewId.getText()));
                Intent intent = new Intent(PostActivity.this, CommentActivity.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void handleSuccessResponse(List<Post> posts) {

        if (posts == null) {
            Toast.makeText(PostActivity.this, "Posts could not be loaded", Toast.LENGTH_SHORT).show();
        }
        adapter = new PostListViewAdapter(getApplicationContext(), posts);
        postListView.setAdapter(adapter);
    }

    @Override
    public void handleFailure(Throwable t) {
        Toast.makeText(PostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }
}

