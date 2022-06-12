package edu.url.salle.albert.gt.evased.lab;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.User;

public class UserLab {
    private ArrayList<User> users;
    private ArrayList<Conversation> conversations;

    public UserLab(int maxUsers){
        users = new ArrayList<>();
        conversations = new ArrayList<>();
        for(int i = 0; i < maxUsers; i++){
            users.add(new User(Integer.toString((int)(Math.random()*100+1)),  Integer.toString((int)(Math.random()*100+1)), Integer.toString((int)(Math.random()*100+1)), Integer.toString((int)(Math.random()*100+1))));
        }
        for(int i = 0; i < maxUsers; i++){
            int max = ((int)(Math.random()*100+1));

            conversations.add(new Conversation( users.get( (int)(Math.random()*maxUsers) ) , users.get( (int)(Math.random()*100) )  ));
            for(int e = 0; e < max; e++){
                conversations.get(i).addMessage(Integer.toString(e), conversations.get(i).getSender());
            }
        }

    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
