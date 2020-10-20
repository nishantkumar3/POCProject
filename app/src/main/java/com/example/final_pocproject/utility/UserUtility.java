package com.example.final_pocproject.utility;

import android.view.View;

import com.example.final_pocproject.api.ApiInterface;
import com.example.final_pocproject.api.RetrofitClient;
import com.example.final_pocproject.interfaces.UserInterface;
import com.example.final_pocproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUtility {

    ApiInterface apiInterface;
    UserInterface userInterface;

    public UserUtility(UserInterface userInterface) {
        this.userInterface = userInterface;
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
    }


    public void getUser() {

        Call<List<User>> call = apiInterface.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                if (userInterface != null) {
                    userInterface.handleSuccessResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (userInterface != null) {
                    userInterface.handleFailure(t);
                }
            }
        });
    }
}
