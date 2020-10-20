package com.example.final_pocproject.api;

import com.example.final_pocproject.model.Comments;
import com.example.final_pocproject.model.Post;
import com.example.final_pocproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}/posts")
    Call<List<Post>> getPosts(@Path("id") int postId);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

}
