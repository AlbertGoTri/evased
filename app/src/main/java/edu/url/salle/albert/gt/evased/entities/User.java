package edu.url.salle.albert.gt.evased.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int userID;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private float avgScore;
    private int numComments;
    private float percentageCommentersBelow;
    private ArrayList<Conversation> convs;
    private ArrayList<User> friends;
    private ArrayList<Event> createdEvents;
    private ArrayList<Event> assistingEvents;

    public User(int userID, String name, String lastName, String email, String password, String image) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.avgScore = 0;
        this.numComments = 0;
        this.percentageCommentersBelow = 0;
        this.friends = new ArrayList<>();
        this.createdEvents = new ArrayList<>();
        this.assistingEvents = new ArrayList<>();
        this.convs = new ArrayList<>();
    }
    public User(int userID, String name, String lastName, String email, String image) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = "password";
        this.image = image;
        this.avgScore = 0;
        this.numComments = 0;
        this.percentageCommentersBelow = 0;
        this.friends = new ArrayList<>();
        this.createdEvents = new ArrayList<>();
        this.assistingEvents = new ArrayList<>();
        this.convs = new ArrayList<>();
    }

    public User(int userID, String name, String lastName, String email, String password, String image, float avgScore, int numComments, float percentageCommentersBelow) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.avgScore = avgScore;
        this.numComments = numComments;
        this.percentageCommentersBelow = percentageCommentersBelow;
        this.friends = new ArrayList<>();
        this.createdEvents = new ArrayList<>();
        this.assistingEvents = new ArrayList<>();
        this.convs = new ArrayList<>();
    }

    public User(int userID, String name, String lastName, String email, String password, String image, float avgScore, int numComments, float percentageCommentersBelow, ArrayList<User> friends, ArrayList<Event> createdEvents, ArrayList<Event> assistingEvents) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.avgScore = avgScore;
        this.numComments = numComments;
        this.percentageCommentersBelow = percentageCommentersBelow;
        this.friends = friends;
        this.createdEvents = createdEvents;
        this.assistingEvents = assistingEvents;
        this.convs = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public float getPercentageCommentersBelow() {
        return percentageCommentersBelow;
    }

    public void setPercentageCommentersBelow(float percentageCommentersBelow) {
        this.percentageCommentersBelow = percentageCommentersBelow;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(ArrayList<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public ArrayList<Event> getAssistingEvents() {
        return assistingEvents;
    }

    public void setAssistingEvents(ArrayList<Event> assistingEvents) {
        this.assistingEvents = assistingEvents;
    }

    public ArrayList<Conversation> getConvs() {
        return convs;
    }

    public void setConvs(ArrayList<Conversation> convs) {
        this.convs = convs;
    }


    public void addConv(Conversation conversation) {
        this.convs.add(conversation);
    }

    public Conversation getLastConv() {
        if(this.convs.size() == 0){
            return null;
        }else {
            return this.convs.get(convs.size() - 1);
        }
    }
}