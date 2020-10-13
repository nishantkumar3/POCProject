package com.example.final_pocproject.Retrofit;

import com.example.final_pocproject.Model.CommentList;
import com.example.final_pocproject.Model.Post;
import com.example.final_pocproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}/posts")
    Call <List<Post>> getPosts(@Path("id") int postId);

    @GET("posts/{id}/comments")
    Call <List<CommentList>> getComments(@Path("id") int postId);

}
