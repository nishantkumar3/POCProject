package com.example.final_pocproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_pocproject.Retrofit.ApiInterface;
import com.example.final_pocproject.Model.Post;
import com.example.final_pocproject.Adapter.PostListViewAdapter;
import com.example.final_pocproject.R;
import com.example.final_pocproject.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private List<Post> postList;
    private ListView postListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent myIntent = getIntent();
        int userId =  myIntent.getIntExtra("id",0);

        postListView = findViewById(R.id.listView);


        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);


        Call <List<Post>> call = apiInterface.getPosts(userId);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                postList =  response.body();

                postListView.setAdapter(new PostListViewAdapter(getApplicationContext(),postList)) ;

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(PostActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewId = view.findViewById(R.id.post_id);
                int postId = Integer.parseInt(String.valueOf(textViewId.getText()));
                Intent intent = new Intent(PostActivity.this, CommentActivity.class);
                intent.putExtra("postId",postId);
                startActivity(intent);
            }
        });
    }
}

