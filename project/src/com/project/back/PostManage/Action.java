package com.project.back.PostManage;

import com.project.back.UserManage.User;

abstract public class Action {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Action(User user) {
        this.user = user;
    }
}
