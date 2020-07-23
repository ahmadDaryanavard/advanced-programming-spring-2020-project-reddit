package com.project.View;

import java.util.Scanner;

public class StartView {
    public static void run(){
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("* Note : In everywhere of this program you can go back only by typing '//' . *");
            System.out.println("1. login \n2. signUp\n3. exit");
            Scanner sc = new Scanner(System.in);
            String select = sc.nextLine();
            if (select.equals("1")) {
                LoginView.loginView();
            } else if (select.equals("2")) {
                SignUpView.signUpView();
            }
            else if (select.equals("3")){
                return;
            }
        }
    }
}
