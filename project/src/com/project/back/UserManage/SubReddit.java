package com.project.back.UserManage;

import com.project.back.PostManage.Post;

import java.util.ArrayList;
import java.util.Comparator;

public class SubReddit {

    private static ArrayList<SubReddit> allSubReddit = new ArrayList<>();
    private User moderator;
    private ArrayList<User> members;
    private String name;
    private String description;

    public ArrayList<User> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<SubReddit> getAllSubReddit() {
        return allSubReddit;
    }

    public User getModerator() {
        return moderator;
    }

    public SubReddit(User moderator, String name) {

        this.moderator = moderator;
        this.name = name;
        this.members = new ArrayList<>();
        this.members.add(moderator);

    }

    public static ArrayList<SubReddit> memberOfSubReddit(User user){
        ArrayList<SubReddit> isMember = new ArrayList<SubReddit>();
        for (SubReddit subReddit : allSubReddit) {
            if(subReddit.getMembers().contains(user)){
                isMember.add(subReddit);
            }

        }
        return isMember;
    }

    public static SubReddit createSubReddit(User user , String name){
        for (SubReddit subReddit : allSubReddit) {
            if (subReddit.getName().equals(name)){
                return null;
            }

        }
        SubReddit subReddit = new SubReddit(user, name);
        getAllSubReddit().add(subReddit);
        return subReddit;
    }

    public void editDescription(String description){
        this.description = description;
    }

    public void deleteMember (User member){
        for (User user : this.getMembers()){
            if (user.equals(member)){
                this.getMembers().remove(member);
                return;
            }
        }
    }

    public void beMember(User newMember){
        for (User user : this.getMembers()) {
            if (user.equals(newMember)){
                return;
            }
        }
        this.getMembers().add(newMember);
    }
    public ArrayList<Post> subRedditPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        for (User user :User.getAllUsers()) {
            if (this.getMembers().contains(user)){
                posts.addAll(Post.userPosts(user));
            }
        }
        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return Integer.compare(post1.showScore(), post2.showScore());
            }
        });
        return posts;

    }

    public static SubReddit searchSubReddit(String subRedditName){
        for (SubReddit subReddit : getAllSubReddit()) {
            if (subReddit.getName().equals(subRedditName)){
                return subReddit;
            }

        }
        return null;
    }
}
