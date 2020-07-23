package com.project.View;

import com.project.back.UserManage.SubReddit;
import com.project.back.UserManage.User;

import java.util.*;

public class SubRedditView {
    public static void subRedditView(User user , SubReddit subReddit){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Description : " + subReddit.getDescription());
            System.out.println("---------------------------------------------------\n");
            if (subReddit.getMembers().contains(user) && !subReddit.getModerator().equals(user)) {
                System.out.println("1.Leave subReddit\n2.View posts\n3.Show members");
                String select = sc.nextLine();
                if (select.equals("1")){
                    subReddit.deleteMember(user);
                    return;
                }
                if (select.equals("2")){
                    AllPostsView.subRedditPostView(subReddit,user);
                }
                if (select.equals("3")){
                	ArrayList<User> users = subReddit.getMembers();
                    Dictionary userNum = new Hashtable();
                    int i = 1;
                    for (User user1 : users){
                        if (subReddit.getModerator().equals(user1)){
                            userNum.put(user1,i);
                            System.out.println("----------------------------");
                            System.out.println(user1.getUserName()+" *Owner*");
                            System.out.println("user number : "+i);
                            i++;
                        }else {
                        	userNum.put(user1,i);
                            System.out.println("----------------------------");
                            System.out.println(user1.getUserName());
                            System.out.println("user number : "+i);
                            i++;
                        }
                    }
                    System.out.println("Enter user number");
                    int sel = -1;
                    String s = sc.nextLine();
                    if(s.equals("//")) {
                    	return;
                    }
                    try {
                        sel = Integer.parseInt(s);
                    }catch (NumberFormatException e){

                    }
                    if (sel != -1){
                        for (User user1 : users) {
                            if ((int)userNum.get(user1) == sel){
                                UserView.userView(user, user1);
                                break;
                            }
                        }
                    }
                }
                if (select.equals("//")){
                    return;
                }


            }

             else if (subReddit.getModerator().equals(user)){
                System.out.println("1.Remove Member\n2.Change description\n3.Delete subReddit\n4.Show Posts");
                String select = sc.nextLine();
                if (select.equals("1")){
                    ArrayList<User> users = subReddit.getMembers();
                    users.remove(user);
                    Dictionary userNum = new Hashtable();
                    int i = 1;
                    for (User user1 : users){
                        if (!subReddit.getModerator().equals(user1)){
                            userNum.put(user1,i);
                            System.out.println("----------------------------");
                            System.out.println(user1.getUserName());
                            System.out.println("user number : "+i);
                            i++;
                        }
                    }
                    System.out.println("Enter user number");
                    int sel = -1;
                    String s = sc.nextLine();
                    if(s.equals("//")) {
                    	return;
                    }
                    try {
                        sel = Integer.parseInt(s);
                    }catch (NumberFormatException e){

                    }
                    if (sel != -1){
                        for (User user1 : users) {
                            if ((int)userNum.get(user1) == sel){
                                subReddit.deleteMember(user1);
                                break;
                            }
                        }
                    }
                }
                if (select.equals("2")){
                    String newDescription = sc.nextLine();
                    subReddit.editDescription(newDescription);
                }
                if (select.equals("3")){
                    SubReddit.getAllSubReddit().remove(subReddit);
                    return;
                }
                if (select.equals("4")){
                    AllPostsView.subRedditPostView(subReddit,user);
                }
                if (select.equals("//")){
                    return;
                }

            }
             else {
                System.out.println("1.Join subReddit\n2.View posts\n3.Show members");
                String select = sc.nextLine();
                if (select.equals("1")){
                    subReddit.beMember(user);
                    
                }
                if (select.equals("2")){
                    AllPostsView.subRedditPostView(subReddit,user);
                }
                if (select.equals("3")){
                	ArrayList<User> users = subReddit.getMembers();
                    Dictionary userNum = new Hashtable();
                    int i = 1;
                    for (User user1 : users){
                        if (subReddit.getModerator().equals(user1)){
                            userNum.put(user1,i);
                            System.out.println("----------------------------");
                            System.out.println(user1.getUserName()+" *Owner*");
                            System.out.println("user number : "+i);
                            i++;
                        }else {
                        	userNum.put(user1,i);
                            System.out.println("----------------------------");
                            System.out.println(user1.getUserName());
                            System.out.println("user number : "+i);
                            i++;
                        }
                    }
                    System.out.println("Enter user number");
                    int sel = -1;
                    String s = sc.nextLine();
                    if(s.equals("//")) {
                    	return;
                    }
                    try {
                        sel = Integer.parseInt(s);
                    }catch (NumberFormatException e){

                    }
                    if (sel != -1){
                        for (User user1 : users) {
                            if ((int)userNum.get(user1) == sel){
                                UserView.userView(user, user1);
                                break;
                            }
                        }
                    }
                }
                if (select.equals("//")){
                    return;
                }
            }
        }
    }


}
