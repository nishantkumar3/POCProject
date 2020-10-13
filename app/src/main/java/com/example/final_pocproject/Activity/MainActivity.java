package com.example.final_pocproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.final_pocproject.Retrofit.ApiInterface;
import com.example.final_pocproject.R;
import com.example.final_pocproject.Model.User;
import com.example.final_pocproject.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Button loginButton;
    private TextInputLayout textInputEmail;
    private ApiInterface apiInterface;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.button);
        textInputEmail = findViewById(R.id.email);

        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

      loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(validateEmail()){
                  getUsers();
              }
          }
      });

    }

    private boolean validateEmail(){
        String inputEmail = textInputEmail.getEditText().getText().toString().trim();

        if(inputEmail.isEmpty()){
            textInputEmail.setError("Email field can't be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            textInputEmail.setError("Please enter a valid email id");
            return false;
        }else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private  void getUsers(){
        Call<List<User>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                 List<User> users = response.body();
                String email = textInputEmail.getEditText().getText().toString();


                int userId = 0;
                for (User user : users){
                    if(user.getEmail().equals(email)){
                        userId=user.getId();

                        break;
                    }
                }

                if(userId==0){
                    textInputEmail.setError("Email id not found");
                }else {
                    Intent intent = new Intent(MainActivity.this, PostActivity.class);
                    intent.putExtra("id",userId);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}