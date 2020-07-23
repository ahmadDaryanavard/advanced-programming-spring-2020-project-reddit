package com.project.back.PostManage;



import com.project.back.Messaging.Chat;
import com.project.back.Messaging.Message;
import com.project.back.Messaging.PostMessage;
import com.project.back.UserManage.SubReddit;
import com.project.back.UserManage.User;
import com.project.back.enums.UserRelationEnum;

import javax.security.auth.login.AccountExpiredException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Dictionary;

public class Post {
    private String content;
    private ArrayList<Action> actions;
    private User user;

    private static ArrayList<Post> allPosts = new ArrayList<>();


    public User getUser() {
        return user;
    }

    public static ArrayList<Post> getAllPosts() {
        return allPosts;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public Post(String content) {

        this.content = content;
    }

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public static void newPost(String content , User user) {

        Post post = new Post(content, user);
        post.actions = new ArrayList<>();
        getAllPosts().add(post);
        return;



    }

    public int showScore(){
        //score
        int finalScore = 0;
        for (Action action : actions) {
            if(action instanceof Score){
                if (((Score) action).isUp()){
                    ++finalScore;

                }
                if (!((Score) action).isUp()){
                    --finalScore;
                }
            }

        }
        return finalScore;
    }

    public void scoreUp(User user){
        for (Action action : this.actions) {
            if (action instanceof Score){
                if (action.getUser().equals(user) && ((Score) action).isUp()){
                    return;
                }else if(action.getUser().equals(user) && ((Score) action).isUp() == false){
                    this.actions.remove(action);
                    this.actions.add(Score.scoreUp(user));
                    return;
                }
            }
        }
        this.actions.add(Score.scoreUp(user));

    }

    public void scoreDown(User user){
        for (Action action : this.actions) {
            if (action instanceof Score){
                if (action.getUser().equals(user) && !((Score) action).isUp()){
                    return;
                }else if(action.getUser().equals(user) && ((Score) action).isUp()){
                    this.actions.remove(action);
                    this.actions.add(Score.scoreDown(user));
                    return;
                }
            }
        }
        this.actions.add(Score.scoreDown(user));
    }



    public void addComment(User user, String text){
        Comment comment = new Comment(user,text);
        this.addAction(comment);
    }

    public void addAction(Action action){
        if (actions == null){
            actions = new ArrayList<Action>();
        }
        this.actions.add(action);

    }

    public static ArrayList<Post> allPosts(User user){
        ArrayList<Post> posts = new ArrayList<Post>(Post.userPosts(user));
        ArrayList<User> followings = user.searchAllUserRelation(UserRelationEnum.FOLLOWING);

        ArrayList<User> subRedditsUsers = new ArrayList<>();
        for (SubReddit subReddit : SubReddit.memberOfSubReddit(user)) {
            subRedditsUsers.addAll(subReddit.getMembers());
        }

        for (Post post : allPosts){
            if (followings.contains(post.getUser())){
                if (!posts.contains(post)) {
                    posts.add(post);
                }
            }
            if (user.getUserSettings().isShowSubRedditPostsInPostPage()){
                if (subRedditsUsers.contains(post.getUser())){
                    if (!posts.contains(post)){
                        posts.add(post);
                    }
                }
            }

        }

        return posts;
    }

    public static ArrayList<Post> userPosts(User user){
        ArrayList<Post> posts = new ArrayList<>();
        for (Post post : getAllPosts()) {
            if (post.getUser().equals(user)){
                posts.add(post);
            }
        }
        return posts;
    }

    public static void deletePost(Post post , User user){
        if (post.getUser().equals(user)){
            getAllPosts().remove(post);
            for (Chat chat : Chat.getAllChats()) {
                for (Message message : chat.getMessages()) {
                    if (message instanceof PostMessage){
                        if (((PostMessage) message).getPost().equals(post)){
                            chat.getMessages().remove(message);
                            break;
                        }
                    }
                }
            }
        }else {
            return;
        }
    }


    //All of post comments not replies of comments
    public ArrayList<Comment> getCm(){
        ArrayList<Comment> cm = new ArrayList<>();
        for (Action action : getActions()) {
            if (action instanceof Comment){
                cm.add((Comment) action);
            }

        }
        return cm;
    }

    public ArrayList<Comment> getCmSortedByScore(){
        ArrayList<Comment> cm = new ArrayList<>();
        for (Action action : getActions()) {
            if (action instanceof Comment){
                cm.add((Comment) action);
            }

        }
        cm.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment comment1, Comment comment2) {
                return Integer.compare(comment2.getScore(),comment1.getScore());
            }
        });
        return cm;
    }


    //delete post comments in here we can't delete replies
    public void deleteCm(Comment comment){
        for (Action action : this.getActions()) {
            if (action instanceof Comment){
                if (action.equals(comment)){
                    this.getActions().remove(action);
                    return;
                }
            }
        }
    }


}
