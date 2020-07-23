package com.project.View;

import com.project.back.UserManage.User;

import java.util.Scanner;

public class SignUpView {
    public static void signUpView(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name");
        String userName = sc.nextLine();
        while(User.searchUser(userName)!=null){
            System.out.println("This user name used , Please try another one");
            userName = sc.nextLine();
            if (userName.equals("//")){
                return;
            }
        }
        System.out.println("Enter your name");
        String name = sc.nextLine();
        if (name.equals("//")){
            return;
        }
        System.out.println("Enter your password");
        String password = sc.nextLine();
        if (password.equals("//")){
            return;
        }
        User user = User.signUp(name,userName,password);
        if (user != null){
            User.logIn(userName,password);
        }
        return;
    }
}
