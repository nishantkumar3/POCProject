package com.example.final_pocproject.utility;

import com.example.final_pocproject.api.ApiInterface;
import com.example.final_pocproject.api.RetrofitClient;
import com.example.final_pocproject.interfaces.PostInterface;
import com.example.final_pocproject.model.Comments;
import com.example.final_pocproject.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostUtility {

    ApiInterface apiInterface;
    PostInterface postInterface;

    public PostUtility(PostInterface postInterface) {
        this.postInterface = postInterface;
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }

    public void getPosts(int userId) {


        Call<List<Post>> call = apiInterface.getPosts(userId);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                if (postInterface != null) {
                    postInterface.handleSuccessResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                if (postInterface != null) {
                    postInterface.handleFailure(t);
                }
            }
        });

    }
}
