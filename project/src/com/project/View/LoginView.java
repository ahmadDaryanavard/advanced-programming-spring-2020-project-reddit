package com.project.View;

import com.project.back.UserManage.User;

import java.util.Scanner;

public class LoginView {
    public static void loginView(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Enter your username");
            String userName = sc.nextLine();
            if (userName.equals("//")){
                return;
            }
            System.out.println("Enter your password");
            String password = sc.nextLine();
            if (password.equals("//")){
                return;
            }
            if (User.logIn(userName,password) == null){
                System.out.println("username or password is wrong please press Enter try again!");
                sc.nextLine();
            }else {
                MainPageView.mainPage(User.logIn(userName,password));
                return;
            }

        }
    }
}
