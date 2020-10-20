package com.example.final_pocproject.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.final_pocproject.api.ApiInterface;
import com.example.final_pocproject.R;
import com.example.final_pocproject.interfaces.UserInterface;
import com.example.final_pocproject.model.User;
import com.example.final_pocproject.api.RetrofitClient;
import com.example.final_pocproject.utility.UserUtility;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserInterface {


    private Button loginButton;
    private TextInputLayout textInputEmail;
    UserUtility userUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.button);
        textInputEmail = findViewById(R.id.email);
        userUtility = new UserUtility(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String inputEmail = Objects.requireNonNull(textInputEmail.getEditText()).getText().toString().trim();

                if (validateEmail(inputEmail)) {
                    userUtility.getUser();
                }
            }
        });

    }

    private boolean validateEmail(String inputEmail) {

        if (inputEmail.isEmpty()) {
            textInputEmail.setError("Email field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            textInputEmail.setError("Please enter a valid email id");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    @Override
    public void handleSuccessResponse(List<User> users) {

        if (users == null) {
            Toast.makeText(MainActivity.this, "Posts could not be loaded", Toast.LENGTH_SHORT).show();
        }

        String email = textInputEmail.getEditText().getText().toString();

        int userId = 0;
        for (User user : users) {
            if (email.equalsIgnoreCase(user.getEmail())) {
                userId = user.getId();

                break;
            }
        }

        if (userId == 0) {
            textInputEmail.setError("Email id not found");
        } else {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            intent.putExtra("id", userId);
            startActivity(intent);
        }
    }

    @Override
    public void handleFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

}