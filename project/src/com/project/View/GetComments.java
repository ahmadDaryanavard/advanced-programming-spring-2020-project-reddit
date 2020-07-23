package com.project.View;

import com.project.back.PostManage.Comment;
import com.project.back.UserManage.User;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class GetComments {

    private Dictionary comments = new Hashtable();
    private ArrayList<Comment> allComments = new ArrayList<>();
    private ArrayList<String> commentString = new ArrayList<>();

    public int CM(ArrayList<Comment> comment , int i, int j , User user){
        for (Comment comment1 : comment){
            comments.put(comment1,i);
            allComments.add(comment1);
            commentString.add(GetComments.padd(j)+i+"."+comment1.showComment()+"\n"
                    +GetComments.padd(j)+"Score : "+comment1.getScore()+"\n"+padd(j));
            int space = j;
            i++;
            if (comment1.getReplies()!= null) {
                j++;
                if (user.getUserSettings().isSortCommentsByScore()) {
                    i = this.CM(comment1.sortByScoreReplies(), i, j , user);
                }else{
                    i = this.CM(comment1.getReplies(), i, j , user);
                }
            }
            j = space;
        }
        return i;
    }
    public Dictionary getComments() {
        return comments;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public ArrayList<String> getCommentString() {
        return commentString;
    }

    public static String padd(int j){
        int m = 0;
        String s = "|";
        String space = "   |";
        while (m < j){
            s = s+space;
            m++;
        }
        return s;
    }
}
