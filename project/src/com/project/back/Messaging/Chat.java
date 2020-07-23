package com.project.back.Messaging;

import com.project.back.PostManage.Post;
import com.project.back.UserManage.User;

import java.util.ArrayList;

public class Chat {

    private User involved1;
    private User involved2;
    private ArrayList<Message> messages;

    private static ArrayList<Chat> allChats = new ArrayList<>();

    public User getInvolved1() {
        return involved1;
    }

    public User getInvolved2() {
        return involved2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public static ArrayList<Chat> getAllChats() {
        return allChats;
    }

    public Chat(User involved1, User involved2) {
        this.involved1 = involved1;
        this.involved2 = involved2;
        this.messages = new ArrayList<>();
    }

    public static Chat newChat(User involved1,User involved2){
        Chat chat = new Chat(involved1,involved2);
        getAllChats().add(chat);
        return chat;
    }

    public ArrayList<String> showChat(User user){
        ArrayList<String> allMessages = new ArrayList<>();
        for (Message message : this.getMessages()) {
            if (message.getUser().equals(user)){
                if (message instanceof TextMessage) {
                    allMessages.add("** You : " + ((TextMessage) message).getText() + " **\n");
                }if (message instanceof PostMessage){
                	allMessages.add("--------------------------------------------------------\n" +
                            "You shared post from : "
                             + ((PostMessage) message).getPost().getUser().getUserName() +
                             "\n\n" +"Content : "+ ((PostMessage) message).getPost().getContent() +
                             "\n--------------------------------------------------------\n");
                }
            }
            else{
                if (message instanceof TextMessage) {
                    if (message.getUser().equals(user)) {
                        allMessages.add("** You : " + ((TextMessage) message).getText() + " **");
                    }else{
                        allMessages.add(message.getUser().getUserName()+" : "+((TextMessage) message).getText()+"\n");
                    }
                }
                if (message instanceof PostMessage){
                     allMessages.add("--------------------------------------------------------\n" +
                         message.getUser().getUserName() + "Shared post from : "
                          + ((PostMessage) message).getPost().getUser().getUserName() +
                          "\n\n" + ((PostMessage) message).getPost().getContent() +
                          "\n--------------------------------------------------------\n");
                    
                }
            }
        }
        return allMessages;
    }

    public void sendTextMessage(String message , User user){
        Message message1 = new TextMessage(user,message);
        this.getMessages().add(message1);
    }

    public void sendPostMessage(Post post, User user){
        Message message = new PostMessage(user,post);
        this.getMessages().add(message);
    }

    public static ArrayList<Chat> searchUserChats(User user){
        ArrayList<Chat> chats = new ArrayList<>();
        for (Chat chat : getAllChats()) {
            if (chat.getInvolved1().equals(user) || chat.getInvolved2().equals(user)){
                chats.add(chat);
            }
        }
        return chats;
    }

    public static Chat searchChat(User involved1, User involved2){
        for (Chat chat : getAllChats()) {
            if (chat.getInvolved1().equals(involved1)){
                if (chat.getInvolved2().equals(involved2)){
                    return chat;
                }
            }
            if (chat.getInvolved2().equals(involved1)){
                if (chat.getInvolved1().equals(involved2)){
                    return chat;
                }
            }
        }
        return null;
    }

}
