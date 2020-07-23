package com.project.View;

import com.project.back.PostManage.Comment;
import com.project.back.Messaging.Chat;
import com.project.back.PostManage.Post;
import com.project.back.UserManage.User;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class PostView {

    public static void postView(User user, Post post){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (post.getUser().equals(user)){
                System.out.println("1.Score Up\n2.Score Down\n3.Comments\n4.Add Comment\n5.Share\n6.Delete post");
            }else {
                System.out.println("1.Score Up\n2.Score Down\n3.Comments\n4.Add Comment\n5.Share");
            }
            String select = sc.nextLine();
            if (select.equals("1")) {
                post.scoreUp(user);
            }
            if (select.equals("2")) {
                post.scoreDown(user);
            }
            if (select.equals("3")) {
                PostView.comments(user, post);
            }
            if (select.equals("4")){
                System.out.println("Enter text");
                String comment = sc.nextLine();
                if (!comment.equals("//")){
                    post.addComment(user,comment);
                }
            }
            if (select.equals("5")){
                ArrayList<Chat> chats = Chat.searchUserChats(user);
                Dictionary chatNum = new Hashtable();
                int i = 1;
                for (Chat chat : chats) {
                    if (chat.getInvolved2().equals(user)){
                        System.out.println(chat.getInvolved1().getUserName());
                        System.out.println("Chat number : "+i);
                        chatNum.put(chat,i);
                        i++;
                    }
                    if (chat.getInvolved1().equals(user)){
                        System.out.println(chat.getInvolved2().getUserName());
                        System.out.println("Chat number : "+i);
                        chatNum.put(chat,i);
                        i++;
                    }
                }
                int cSelect = -1;
                String s = sc.nextLine();
                if (s.equals("//")){
                    return;
                }
                try {
                    cSelect = Integer.parseInt(s);
                }catch (NumberFormatException e){
                    //
                }
                if (cSelect != -1) {
                    for (Chat chat : chats) {
                        if ((int) chatNum.get(chat) == cSelect) {
                            chat.sendPostMessage(post,user);
                            break;
                        }
                    }
                }
            }
            if (select.equals("6") && post.getUser().equals(user)){
                Post.deletePost(post,user);
                post = null;
                return;
            }
            if (select.equals("//")) {
                return;
            }
        }
    }

    public static void comments(User user, Post post){
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Scanner sc = new Scanner(System.in);
            GetComments gC = new GetComments();
            if (user.getUserSettings().isSortCommentsByScore()) {
                gC.CM(post.getCmSortedByScore(), 1, 0, user);
            }else {
                gC.CM(post.getCm(), 1, 0, user);
            }
            ArrayList<Comment> allComments = gC.getAllComments();
            Dictionary commentsNum = gC.getComments();
            ArrayList<String> commentString = gC.getCommentString();

            for (String comment : commentString) {
                System.out.println(comment);
            }

            System.out.println("\nEnter comment number");
            String commentSelected = sc.nextLine();
            int cSelect = -1;
            if (commentSelected.equals("//")) {
                return;
            }
            try {
                cSelect = Integer.parseInt(commentSelected);
            } catch (NumberFormatException e) {
                System.out.println("This is not number");
                return;
            }
            if (cSelect != -1) {
                for (Comment comment : allComments) {
                    if ((int) commentsNum.get(comment) == cSelect) {
                        if (comment.getUser().equals(user) || post.getUser().equals(user)){
                            System.out.println("1.Score up\n2.Score down\n3.Reply\n4.Delete");
                        }else {
                            System.out.println("1.Score up\n2.Score down\n3.Reply");
                        }
                        String option = sc.nextLine();
                        if (option.equals("1")) {
                            comment.scoreUp(user);
                        }
                        if (option.equals("2")) {
                            comment.scoreDown(user);
                        }
                        if (option.equals("3")) {
                            System.out.println("Enter text");
                            String replyText = sc.nextLine();
                            if (replyText.equals("//")) {
                                break;
                            } else {
                                comment.reply(user, replyText);
                                System.out.println("your reply sent successfully");
                                return;
                            }
                        }
                        if (option.equals("4")){
                            if (comment.getUser().equals(user) || post.getUser().equals(user)){
                                if (post.getCm().contains(comment)){
                                    post.deleteCm(comment);
                                }else {
                                    for (Comment comment1 : allComments) {
                                        comment1.deleteReply(comment);
                                    }
                                }
                            }
                        }
                        if (option.equals("//")) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void deleteComment(ArrayList<Comment> comments, Comment toDelete){
        for (Comment comment : comments) {
            if (comment!=toDelete) {
                if (comment.getReplies() != null) {
                    if (comment.getReplies().contains(toDelete)){
                        comment.deleteReply(toDelete);
                        break;
                    }else {
                        deleteComment(comment.getReplies(),comment);
                    }
                }
            }
        }
    }



}
