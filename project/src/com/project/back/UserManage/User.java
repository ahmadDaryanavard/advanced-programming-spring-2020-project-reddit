package com.project.back.UserManage;

import com.project.back.enums.UserRelationEnum;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();

    private String name;
    private String userName;
    private String password;
    private String bio;
    private UserSettings userSettings;
    private ArrayList<UserRelation> userRelations;



    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<UserRelation> getUserRelations() {
        return userRelations;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userRelations = new ArrayList<>();
        this.userSettings = new UserSettings();
    }

    public void changePassword(String password){
        this.password = password;
    }

    public static User signUp(String name, String userName, String password){
        for (User user : allUsers) {
            if(user.getUserName().equals(userName)){
                return null;
            }

        }
        User newUser = new User(name , userName, password);
        allUsers.add(newUser);
        return newUser;
    }

    public static User logIn(String userName , String password){
        for (User user  : allUsers) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }


    public void follow(User user){
        if(!CheckFollowed(user)){
            if (this.userRelations == null ){
                this.userRelations = new ArrayList<UserRelation>();
            }
            this.userRelations.add(new UserRelation(UserRelationEnum.FOLLOWING,user));
            user.getUserRelations().add(new UserRelation(UserRelationEnum.FOLLOWER,this));
        }
    }

    public void unFollow(User user){
        if (this.userRelations!=null){
           UserRelation userRelation = searchUserRelation(UserRelationEnum.FOLLOWING,user);
            if (userRelation!=null){
                this.userRelations.remove(userRelation);
                user.getUserRelations().remove(searchUserRelation(UserRelationEnum.FOLLOWER,this));
            }
        }
    }


    public boolean CheckFollowed(User user){
        for (UserRelation userRelation:this.getUserRelations()) {
            if (userRelation.getUser().equals(user) && userRelation.getUserRelation().equals(UserRelationEnum.FOLLOWING)){
                return true;
            }
        }
        return false;
    }

    public static User searchUser(String userName){
        for (User user : allUsers) {
            if(user.getUserName().equals(userName)){
                return user;
            }

        }
        return null;
    }
    public UserRelation searchUserRelation(UserRelationEnum userRelation, User user){
        for (UserRelation userRelation1 : this.userRelations){
            if (userRelation1.getUserRelation().equals(userRelation) && userRelation1.getUser().equals(user)){
                return userRelation1;
            }
        }
        return null;
    }

    public ArrayList<User> searchAllUserRelation(UserRelationEnum userRelation){
        ArrayList<User> users = new ArrayList<>();
        for (UserRelation userRelation1 : this.userRelations){
            if (userRelation1.getUserRelation().equals(userRelation)){
                users.add(userRelation1.getUser());
            }
        }
        return users;
    }

    public void editBio(String bioText){
        this.bio = bioText;

    }

    public static ArrayList<User> showFollowers(User user){
        ArrayList<User> followers = new ArrayList<>();
        if (user.getUserRelations()!=null) {
            for (UserRelation userRelation : user.getUserRelations()) {
                if (userRelation.getUserRelation().equals(UserRelationEnum.FOLLOWER)) {
                    followers.add(user);
                }
            }
            return followers;
        }
        return null;
    }

    public static ArrayList<User> showFollowings(User user){
        ArrayList<User> followings = new ArrayList<>();
        if (user.getUserRelations()!=null) {
            for (UserRelation userRelation : user.getUserRelations()) {
                if (userRelation.getUserRelation().equals(UserRelationEnum.FOLLOWING)) {
                    followings.add(user);
                }
            }
            return followings;
        }
        return null;
    }

    public ArrayList<User> userFollowings(){
        ArrayList<User> followings = new ArrayList<>();
        for (UserRelation userRelation : this.getUserRelations()){
            if (userRelation.getUserRelation().equals(UserRelationEnum.FOLLOWING)){
                followings.add(userRelation.getUser());
            }
        }
        return followings;
    }

    public ArrayList<User> userFollowers(){
        ArrayList<User> followers = new ArrayList<>();
        for (UserRelation userRelation : this.getUserRelations()){
            if (userRelation.getUserRelation().equals(UserRelationEnum.FOLLOWER)){
                followers.add(userRelation.getUser());
            }
        }
        return followers;
    }


}
