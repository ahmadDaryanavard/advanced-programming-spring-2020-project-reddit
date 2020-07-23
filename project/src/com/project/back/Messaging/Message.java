package com.project.back.Messaging;

import com.project.back.UserManage.User;

abstract public class Message {

    private User user;

    public Message(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
