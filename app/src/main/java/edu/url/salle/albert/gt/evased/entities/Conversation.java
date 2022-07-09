package edu.url.salle.albert.gt.evased.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {


    //-------------------------------ATTRIBUTES--------------------------------------------------------

    private int id;
    private String name;
    private String last_name;
    private String email;
    private String image;
    private ArrayList<Message> messages;

    public Conversation(int id, String name, String last_name, String email, String image) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.image = image;
        this.messages = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addMess(Message message) {
        this.messages.add(message);
    }

    public Message getLastMessage() {
        if(messages.size() == 0){
            return null;
        }
        return this.messages.get(messages.size() - 1);
    }
}


