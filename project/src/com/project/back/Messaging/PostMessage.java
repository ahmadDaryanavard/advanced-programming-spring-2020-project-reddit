package com.project.back.Messaging;

import com.project.back.PostManage.Post;
import com.project.back.UserManage.User;

public class PostMessage extends Message {

    private Post post;

    public PostMessage(User user, Post post) {
        super(user);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

}
