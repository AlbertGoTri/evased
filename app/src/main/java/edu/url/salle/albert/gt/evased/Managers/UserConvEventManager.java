package edu.url.salle.albert.gt.evased.Managers;

import java.io.Serializable;
import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;
import edu.url.salle.albert.gt.evased.entities.User;
import edu.url.salle.albert.gt.evased.lab.EventLab;
import edu.url.salle.albert.gt.evased.lab.UserLab;

public class UserConvEventManager implements Serializable {

    private ArrayList<User> users;
    private ArrayList<Conversation> conversations;
    private ArrayList<Event> events;

    public UserConvEventManager(){

        //TODO: Instead of getting the DATA from the Labs, we should get the DATA from the Shared Preferences and API

        //---------------------------------------------------------------------------------INITIALIZE USERS + CONVERSATIONS + EVENTS
        UserLab userlab = new UserLab();
        EventLab eventlab = new EventLab( userlab.getUsers()); //always initialize users lab before this one

        this.conversations = userlab.getConversations();
        this.events = eventlab.getEvents();
        this.users = userlab.getUsers();
    }

    //--------------------------------------------------------------------------------------Special Functions

    public ArrayList<Conversation> getRelatedConversations(User user){
        //arraylist with the converations with the user participates
        ArrayList<Conversation> final_conversations = new ArrayList<>();

        //get only the conversations where user participates
        for(Conversation conv: this.conversations){
            System.out.println("all message:  ------------ " + conv.getLastMessage().getContent());
            if(conv.getReceiver().getName().equals(user.getName()) || conv.getSender().getName().equals(user.getName())){
                final_conversations.add(conv);
                System.out.println("messages:     " + conv.getLastMessage().getContent());
            }
        }
        return final_conversations;
    }


    //---------------------------------------------------------------------------------------GETTERS


    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
