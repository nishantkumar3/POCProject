package com.example.final_pocproject.interfaces;

import com.example.final_pocproject.model.Comments;

import java.util.List;

public interface CommentInterface {
    void handleSuccessResponse(List<Comments> comments);

    void handleFailure(Throwable t);
}
