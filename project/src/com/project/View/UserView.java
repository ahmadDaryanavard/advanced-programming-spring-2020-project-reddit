package com.project.View;

import com.project.back.Messaging.Chat;
import com.project.back.UserManage.User;

import java.util.Scanner;

public class UserView {
    public static void userView(User user , User viewed){
        Scanner sc = new Scanner(System.in);
        if(user.equals(viewed)) {
        	MainPageView.mainPage(user);
        	return;
        	
        }
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Username : " + viewed.getUserName());
            if (!viewed.getUserSettings().isHideRealName()) {
                System.out.println("Name: " + viewed.getName());
            }
            System.out.println("Description : " + viewed.getBio());
            if (user.CheckFollowed(viewed)) {
                System.out.println("1.UnFollow\n2.View all posts\n3.Send Message");
                String select = sc.nextLine();
                if (select.equals("1")) {
                    user.unFollow(viewed);
                }
                if (select.equals("2")) {
                    AllPostsView.userPostView(viewed,user);
                }
                if (select.equals("3")) {
                    Chat chat = Chat.searchChat(user, viewed);
                    if (chat == null){
                        chat = Chat.newChat(user, viewed);
                    }
                    ChatView.chatView(chat,user);
                }
                if (select.equals("//")){
                    return;
                }
            }
            else {
                System.out.println("1.Follow\n2.View all posts\n3.Send Message");
                String select = sc.nextLine();
                if (select.equals("1")) {
                    user.follow(viewed);
                }
                if (select.equals("2")) {
                    AllPostsView.userPostView(viewed,user);
                }
                if (select.equals("3")) {
                    Chat chat = Chat.searchChat(user, viewed);
                    if (chat == null){
                        chat = Chat.newChat(user, viewed);
                    }
                    ChatView.chatView(chat,user);
                }
                if (select.equals("//")){
                    return;
                }
            }
        }
    }
}
