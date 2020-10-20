package com.example.final_pocproject.utility;

import com.example.final_pocproject.interfaces.CommentInterface;
import com.example.final_pocproject.api.ApiInterface;
import com.example.final_pocproject.api.RetrofitClient;
import com.example.final_pocproject.model.Comments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentUtility {

    ApiInterface apiInterface;
    CommentInterface commentInterface;

    public CommentUtility(CommentInterface commentInterface) {
        this.commentInterface = commentInterface;
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }

    public void getComments(int postId) {

        Call<List<Comments>> call = apiInterface.getComments(postId);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                if (commentInterface != null) {
                    commentInterface.handleSuccessResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

                if (commentInterface != null) {
                    commentInterface.handleFailure(t);
                }
            }
        });

    }
}
