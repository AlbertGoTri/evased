package edu.url.salle.albert.gt.evased.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {


    //-------------------------------ATTRIBUTES--------------------------------------------------------
    private ArrayList<Message> messages;
    private User receiver;
    private User sender;


    //--------------------------------CONSTRUCTORS-----------------------------------------------------
    //we start the conversation only knowing the user who is going to recieve the messages
    public Conversation(User receiver, User sender){
        this.receiver = receiver;
        this.sender = sender;
        this.messages = new ArrayList<>();

    }

    //------------------------------GETTERS------------------------------------------------------------
    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    //last message getter
    public Message getLastMessage(){
        if(messages.size() == 0){
            return new Message("No Messages received yet :(( (incel)", 0, new User("Marcel", "Lopez", "elPikoDeOro@gmail.com", "password"));
        }
        return this.messages.get(messages.size() - 1);
    }

    //single message getter
    public Message getSingleMessage(int index){
        return this.messages.get(index);
    }


    //------------------------------SETTERS------------------------------------------------------------
    public void addMessage(String content, User sender){
        this.messages.add(new Message(content, (int)System.currentTimeMillis(), sender));

    }

}
