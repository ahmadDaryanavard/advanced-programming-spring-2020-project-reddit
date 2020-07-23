package com.project.View;

import com.project.back.PostManage.Post;
import com.project.back.UserManage.SubReddit;
import com.project.back.UserManage.User;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class MainPageView {
    private static Object EOFException;

    public static void mainPage(User user){

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("1.Go to post\n2.My posts\n3.New Post\n4.Go to messages\n5.Show my subReddit\n6.Search user" +
                    "\n7.search subReddit\n8.New subReddit\n9.Show my followings\n10.Show my followers\n11.Account settings" +
                    "\n12.Exit");
            String select = sc.nextLine();
            if (select.equals("1")){
                AllPostsView.allPostsView(user);
            }
            if (select.equals("2")){
                postManage(user);
            }
            if (select.equals("3")){
                newPostView(user);
            }
            if (select.equals("4")){
                ChatView.getAllChats(user);
            }
            if (select.equals("5")){
                subRedditManage(user);
            }
            if (select.equals("6")){
                searchUserView(user);
            }
            if (select.equals("7")){
                searchSubRedditView(user);
            }if (select.equals("//")){
                return;
            }
            if (select.equals("8")){
                newSubRedditView(user);
            }
            if (select.equals("9")){
                showFollowings(user);
            }
            if (select.equals("10")){
                showFollowers(user);
            }
            if (select.equals("11")){
                showSettings(user);
            }
            if (select.equals("12")){
                System.exit(0);
            }
        }
    }

    public static void postManage(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<Post> posts = Post.userPosts(user);
            Dictionary postNum = new Hashtable();
            int i = 1;
            for (Post post : posts) {
                postNum.put(post,i);
                System.out.println("------------------------------------------------");
                System.out.println("Content : "+post.getContent());
                System.out.println("Score : "+post.showScore());
                System.out.println("post number : "+i);
                i++;
            }
            System.out.println("\n\nEnter post number");
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            int select = -1;
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){

            }
            if (select != -1){
                for (Post post : posts){
                    if ((int)postNum.get(post)==select){
                        PostView.postView(user,post);
                        postManage(user);
                        return;
                    }
                }
                System.out.println("post with this number not found!");
            }
        }

    }
    public static void subRedditManage(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<SubReddit> userSubReddit = SubReddit.memberOfSubReddit(user);
            Dictionary<SubReddit, Integer> subRedditNum = new Hashtable();
            int i = 1;
            System.out.println("your subReddits : ");
            System.out.println("##################################################################");
            for (SubReddit subReddit : SubReddit.getAllSubReddit()) {
                if (subReddit.getModerator().equals(user)) {
                    subRedditNum.put(subReddit, i);
                    System.out.println("SubReddit name : " + subReddit.getName());
                    System.out.println("Number of members : " + subReddit.getMembers().size());
                    System.out.println("subReddit number : " + i);
                    System.out.println("------------------------------------------------------------------");
                    i++;
                }
            }
            System.out.println("##################################################################\n");
            System.out.println("SubReddits you joined :\n");
            System.out.println("##################################################################\n");
            for (SubReddit subReddit : SubReddit.getAllSubReddit()) {
                if (subReddit.getMembers().contains(user) && !subReddit.getModerator().equals(user)) {
                    subRedditNum.put(subReddit, i);
                    System.out.println("SubReddit name : " + subReddit.getName());
                    System.out.println("Number of members : " + subReddit.getMembers().size());
                    System.out.println("subReddit number : " + i);
                    System.out.println("------------------------------------------------------------------");
                    i++;
                }
            }
            System.out.println("##################################################################\n");

            System.out.println("Enter subReddit number");
            int select = -1;
            String s = sc.nextLine();
            if (s.equals("//")) {
                return;
            }
            try {
                select = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("This is not number");
            }
            if (select != -1) {
                for (SubReddit subReddit : userSubReddit) {
                    if ((int) subRedditNum.get(subReddit) == select) {
                        SubRedditView.subRedditView(user,subReddit);
                        return;
                    }
                }
            }
        }
    }
    public static void newSubRedditView(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Enter your subReddit name");
            String subRedditName = sc.nextLine();
            if (subRedditName.equals("//")) {
                return;
            }
            for (SubReddit subReddit : SubReddit.getAllSubReddit()) {
                if (subReddit.getName().equals(subRedditName)) {
                    System.out.println("This name used before");
                    return;
                }
            }
            SubReddit subReddit = SubReddit.createSubReddit(user, subRedditName);
            SubRedditView.subRedditView(user, subReddit);
        }
    }
    public static void searchUserView(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Enter username");
            String userName = sc.nextLine();
            if (userName.equals("//")){
                return;
            }
            User user1 = User.searchUser(userName);
            
            if (user1!=null){
                UserView.userView(user,user1);
            }
        }
    }

    public static void searchSubRedditView(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Enter subReddit name");
            String subRedditName = sc.nextLine();
            if (subRedditName.equals("//")){
                return;
            }
            if (SubReddit.searchSubReddit(subRedditName)==null){
                System.out.println("there is no subReddit with this name");
            }else{
                SubRedditView.subRedditView(user,SubReddit.searchSubReddit(subRedditName));
                return;
            }
        }
    }

    public static void newPostView(User user){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Enter text\nNote : for finishing your text press Enter then type '///' and then" +
                " press Enter again.");
        Scanner sc = new Scanner(System.in);
        String content = "";
        while (true){

            String s = sc.nextLine();
            if (content.equals("") && s.equals("//")){
               return;
            }
            if (s.equals("///")){
                break;
            }
            content = content + s +"\n";
        }
        Post.newPost(content,user);
    }

    public static void showFollowings(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<User> followings = user.userFollowings();
            Dictionary followingNum = new Hashtable();
            int i = 1;
            for (User following : followings) {
                followingNum.put(following,i);
                System.out.println("--------------------------------------------------------");
                System.out.println("UserName : "+following.getUserName());
                System.out.println("User number : "+i);
                System.out.println("--------------------------------------------------------\n");
                i++;
            }
            System.out.println("Enter user number");
            int select = -1;
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){
                System.out.println("This is not number");
            }
            if (select != -1){
                for (User following : followings){
                    if ((int)followingNum.get(following) == select){
                        UserView.userView(user,following);
                        return;
                    }
                }
            }
        }
    }

    public static void showFollowers(User user){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<User> followers = user.userFollowers();
            Dictionary followerNum = new Hashtable();
            int i = 1;
            for (User follower : followers) {
                followerNum.put(follower,i);
                System.out.println("--------------------------------------------------------");
                System.out.println("UserName : "+follower.getUserName());
                System.out.println("User number : "+i);
                System.out.println("--------------------------------------------------------\n");
                i++;
            }
            int select = -1;
            System.out.println("Enter user number");
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){
                System.out.println("This is not number");
            }
            if (select != -1){
                for (User follower : followers){
                    if ((int)followerNum.get(follower) == select){
                        UserView.userView(user,follower);
                        return;
                    }
                }
            }
        }
    }

    public static void showSettings(User user){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("1.Hide name : ");
            if (user.getUserSettings().isHideRealName()){
                System.out.println("on");
            }else {
                System.out.println("off");
            }
            System.out.println("");
            System.out.print("2.Sort comments by score : ");
            if (user.getUserSettings().isSortCommentsByScore()){
                System.out.println("on");
            }else {
                System.out.println("off");
            }
            System.out.println("");
            System.out.print("3.Sort posts by score : ");
            if (user.getUserSettings().isSortPostsByScore()){
                System.out.println("on");
            }else {
                System.out.println("off");
            }
            System.out.println("");
            System.out.print("4.Show subReddit posts in all post view : ");
            if (user.getUserSettings().isShowSubRedditPostsInPostPage()){
                System.out.println("on");
            }else {
                System.out.println("off");
            }
            System.out.println("");
            System.out.println("5.Change password\n");
            System.out.println("Enter number you want to change");
            String select = sc.nextLine();
            if (select.equals("1")){
                user.getUserSettings().changeHideRealName();
            }
            if (select.equals("2")){
                user.getUserSettings().changeCommentByScore();
            }
            if (select.equals("3")){
                user.getUserSettings().changePostByScore();
            }
            if (select.equals("4")){
                user.getUserSettings().changeShowSubReddit();
            }
            if (select.equals("5")){
                System.out.println("Enter your current password");
                String currentPassword = sc.nextLine();
                if (User.logIn(user.getUserName(),currentPassword) == user){
                    System.out.println("Enter your new password");
                    String newPassword = sc.nextLine();
                    user.changePassword(newPassword);
                    System.out.println("your password changed successfully!");
                }else {
                    System.out.println("Current password is wrong\nPress Enter to go back");
                    sc.nextLine();
                }
            }
            if (select.equals("//")){
                return;
            }
        }
    }
}
