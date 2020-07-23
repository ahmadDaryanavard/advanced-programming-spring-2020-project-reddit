package com.project.back.PostManage;

import com.project.back.UserManage.User;

import java.util.*;

public class Comment extends Action {

    private String text;
    private ArrayList<Action> actions;


    public Comment(User user, String text) {
        super(user);
        this.text = text;
        this.actions = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public static void comment(User user, String text, Post post){
        Comment comment = new Comment(user,text);
        post.addAction(comment);

    }

    public static void deleteComment(Comment comment , Post post){
        //sdkljfas

    }

    public String showComment(){
        return (getUser().getUserName() + " : "+ this.text);
    }

    public int getScore(){
        int score = 0;
        for (Action action : this.actions){
            if (action instanceof Score){
                if (((Score) action).isUp()){
                    score++;
                }else{
                    score--;
                }
            }
        }
        return score;
    }
    public ArrayList<Comment> getReplies(){
        ArrayList<Comment> comments = new ArrayList<>();
        for (Action action : this.actions) {
            if (action instanceof Comment){
                comments.add((Comment)action);
            }

        }
        return comments;
    }

    public void reply(User user,String text){
        Comment comment = new Comment(user,text);
        this.actions.add(comment);
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

    public Dictionary comments(){
        Dictionary comAndRep = new Hashtable();
        for (Comment reply : this.getReplies()) {
            comAndRep.put(reply,new Hashtable());
            for (Comment rr : this.getReplies()){
                if (!rr.equals(null)){
                    ((Dictionary)comAndRep.get(reply)).put(rr,rr.comments());
                }
            }
        }
        return comAndRep;
    }

    public ArrayList<Comment> sortByScoreReplies(){
        ArrayList<Comment> replies = new ArrayList<>();
        replies.addAll(this.getReplies());
        replies.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment comment1, Comment comment2) {
                return Integer.compare(comment2.getScore(),comment1.getScore());
            }
        });

        return replies;
    }
    public void deleteReply(Comment reply){
        for (Action action : getActions()) {
            if (action instanceof Comment){
                if (action.equals(reply)){
                    getActions().remove(action);
                    return;
                }
            }
        }
    }
}
