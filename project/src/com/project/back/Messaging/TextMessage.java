package com.project.back.Messaging;

import com.project.back.UserManage.User;

public class TextMessage extends Message{

    private String text;

    public TextMessage(User user, String text) {
        super(user);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
