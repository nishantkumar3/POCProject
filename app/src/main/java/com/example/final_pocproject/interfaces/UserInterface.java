package com.example.final_pocproject.interfaces;

import com.example.final_pocproject.model.User;

import java.util.List;

public interface UserInterface {
    void handleSuccessResponse(List<User> users);

    void handleFailure(Throwable t);
}
