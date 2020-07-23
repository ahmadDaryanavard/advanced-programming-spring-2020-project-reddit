package com.project.back.UserManage;

import com.project.back.enums.UserRelationEnum;

public class UserRelation {
    private UserRelationEnum userRelation;
    private User user;

    public UserRelationEnum getUserRelation() {
        return userRelation;
    }
    public User getUser() {
        return user;
    }

    public UserRelation(UserRelationEnum userRelation, User user) {
        this.userRelation = userRelation;
        this.user = user;
    }
}
