package com.project.View;

import com.project.back.PostManage.Post;
import com.project.back.UserManage.SubReddit;
import com.project.back.UserManage.User;

import java.util.*;

public class AllPostsView {
    public static void allPostsView(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<Post> posts = Post.allPosts(user);
            if (user.getUserSettings().isSortPostsByScore()){
                posts.sort(new Comparator<Post>() {
                    @Override
                    public int compare(Post post1, Post post2) {
                        return Integer.compare( post2.showScore(),post1.showScore());
                    }
                });
            }
            Dictionary postNum = new Hashtable();
            int i = 1;
            for (Post post : posts) {
                postNum.put(post, i);
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("UserName : "+post.getUser().getUserName());
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("#########################################################################");
                System.out.println("Content : "+post.getContent());
                System.out.println("#########################################################################");
                System.out.println("Score : "+post.showScore());
                System.out.println("* Post number : " + i + " *");
                System.out.println("\n");
                i++;
            }
            System.out.println("Enter post number");
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            int select = -1;
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){
                System.out.println("this is not number");
            }
            if (select != -1){
                for (Post post : posts){
                    if ((int)postNum.get(post) == select){
                        PostView.postView(user,post);
                        break;
                    }
                }
            }
        }

    }

    public static void userPostView(User userP , User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<Post> posts = Post.userPosts(userP);
            if (user.getUserSettings().isSortPostsByScore()){
                posts.sort(new Comparator<Post>() {
                    @Override
                    public int compare(Post post1, Post post2) {
                        return Integer.compare(post2.showScore(),post1.showScore());
                    }
                });
            }
            Dictionary postNum = new Hashtable();
            int i = 1;
            for (Post post : posts){
                postNum.put(post,i);
                System.out.println("-----------------------------------------------------------\n");
                System.out.println("Content : "+post.getContent());
                System.out.println("............................\n");
                System.out.println("Score : "+post.showScore());
                System.out.println("............................\n");
                System.out.println("Post number : "+i);
            }
            System.out.println("\nEnter post number");
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            int select = -1;
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){

            }
            if (select != -1) {
                for (Post post : posts) {
                    if ((int) postNum.get(post) == select){
                        PostView.postView(user,post);
                        break;
                    }
                }
            }
        }
    }

    public static void subRedditPostView(SubReddit subReddit , User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<Post> posts = subReddit.subRedditPosts();
            if (user.getUserSettings().isSortPostsByScore()){
                posts.sort(new Comparator<Post>() {
                    @Override
                    public int compare(Post post1, Post post2) {
                        return Integer.compare(post2.showScore(),post1.showScore());
                    }
                });
            }
            Dictionary postNum = new Hashtable();
            int i = 1;
            for (Post post : posts) {
                postNum.put(post, i);
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("UserNmae : "+post.getUser().getUserName());
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("#########################################################################");
                System.out.println("Content : "+post.getContent());
                System.out.println("#########################################################################");
                System.out.println("* Post number : " + i + " *");
                System.out.println("\n");
                i++;
            }
            System.out.println("Enter post number");
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            int select = -1;
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){
                System.out.println("this is not number");
            }
            if (select != -1){
                for (Post post : posts){
                    if ((int)postNum.get(post) == select){
                        PostView.postView(user,post);
                        break;
                    }
                }
            }
        }
    }
}
