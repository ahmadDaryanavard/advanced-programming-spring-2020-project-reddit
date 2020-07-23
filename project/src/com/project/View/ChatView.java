package com.project.View;

import com.project.back.Messaging.Chat;
import com.project.back.UserManage.User;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class ChatView {
    public static void getAllChats(User user){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
            int select = -1;
            String s = sc.nextLine();
            if (s.equals("//")){
                return;
            }
            try {
                select = Integer.parseInt(s);
            }catch (NumberFormatException e){
                //
            }
            if (select != -1){
                for (Chat chat : chats) {
                    if ((int)chatNum.get(chat)==select){
                        ChatView.chatView(chat,user);
                        break;
                    }
                }
            }
        }
    }

    public static void chatView(Chat chat, User user){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (String message : chat.showChat(user)){
                System.out.println(message);
            }
            System.out.println("");

            String newMessage = sc.nextLine();
            if (newMessage.equals("//")){
                return;
            }else{
                chat.sendTextMessage(newMessage,user);
            }
        }
    }
}
