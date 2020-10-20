package com.example.final_pocproject.interfaces;


import com.example.final_pocproject.model.Post;

import java.util.List;

public interface PostInterface {
    void handleSuccessResponse(List<Post> posts);

    void handleFailure(Throwable t);
}
