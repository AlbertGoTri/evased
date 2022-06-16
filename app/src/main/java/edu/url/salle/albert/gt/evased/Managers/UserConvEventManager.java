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

    public UserConvEventManager() {

        //TODO: Instead of getting the DATA from the Labs, we should get the DATA from the Shared Preferences and API

        //---------------------------------------------------------------------------------INITIALIZE USERS + CONVERSATIONS + EVENTS
        //UserLab userlab = new UserLab();
        //EventLab eventlab = new EventLab(userlab.getUsers()); //always initialize users lab before this one

        //this.conversations = userlab.getConversations();
        //this.events = eventlab.getEvents();
        //this.users = userlab.getUsers();
    }

    //--------------------------------------------------------------------------------------Special Functions GETTERS

    public ArrayList<Conversation> getRelatedConversations(User user) {
        //arraylist with the converations with the user participates
        ArrayList<Conversation> final_conversations = new ArrayList<>();

        //get only the conversations where user participates
        for (Conversation conv : this.conversations) {

            if (conv.getReceiver().getName().equals(user.getName()) || conv.getSender().getName().equals(user.getName())) {
                final_conversations.add(conv);

            }
        }
        return final_conversations;
    }

    public Conversation getOneConveration(User signInUser, User otherUser) {
        Conversation convv = null;
        for (Conversation conv : conversations) {
            if ((conv.getSender().getName().equals(signInUser.getName()) && conv.getReceiver().getName().equals(otherUser.getName()))
                    || (conv.getReceiver().getName().equals(signInUser.getName()) && conv.getSender().getName().equals(otherUser.getName())))
            {
                convv = conv;
            }
        }
        return convv;
    }

    public User getTheOtherUser(Conversation item, User signInUser) {

        if(item.getSender().getName().equals(signInUser.getName())){
            return item.getReceiver();
        }else{
            return item.getSender();
        }
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


    //--------------------------------------------------------------------------------------Special Functions SETTERS

    public void addMessage(String content, User sender, User receiver){
        Conversation conv = this.getOneConveration(sender, receiver);
        conv.addMessage(content, sender);
    }

    public ArrayList<User> getSimilarUsers(CharSequence userToSearch) {
        ArrayList<User> usersToReturn = new ArrayList<>();
        for(User user: this.users){
            if(userToSearch.charAt(0) == user.getName().charAt(0)){
                usersToReturn.add(user);
            }
        }
        return usersToReturn;
    }
}
