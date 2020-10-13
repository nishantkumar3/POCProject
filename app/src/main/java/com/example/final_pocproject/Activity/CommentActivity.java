package com.example.final_pocproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.final_pocproject.Model.CommentList;
import com.example.final_pocproject.Adapter.CommentListViewAdapter;
import com.example.final_pocproject.Retrofit.ApiInterface;
import com.example.final_pocproject.R;
import com.example.final_pocproject.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private ListView commentListView;
    List<CommentList> commentList;
    CommentListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentListView = findViewById(R.id.commentListView);

        Intent myIntent = getIntent();
        int postId =  myIntent.getIntExtra("postId",0);



        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);



        Call<List<CommentList>> call = apiInterface.getComments(postId);

        call.enqueue(new Callback<List<CommentList>>() {
            @Override
            public void onResponse(Call<List<CommentList>> call, Response<List<CommentList>> response) {

                commentList =  response.body();
                adapter = new CommentListViewAdapter(getApplicationContext(),commentList);
                commentListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CommentList>> call, Throwable t) {
                Toast.makeText(CommentActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}