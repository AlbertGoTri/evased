package edu.url.salle.albert.gt.evased.entities;


import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {


    //-------------------------------ATTRIBUTES--------------------------------------------------------
    private String name;
    private String description;
    private Date creation_date;
    private Date end_date;
    private boolean finished;
    private User user;

    //--------------------------------CONSTRUCTORS-----------------------------------------------------

    public Event(String name, String description, Date end_date, User userr) {
        this.name = name;
        this.description = description;
        this.creation_date = new Date(System.currentTimeMillis());
        this.end_date = end_date;
        this.finished = false;
        this.user = userr;
    }


    //------------------------------GETTERS------------------------------------------------------------


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public boolean isFinished() {
        return finished;
    }

    public User getUser() {
        return user;
    }

    //------------------------------SETTERS------------------------------------------------------------
    public void taskFinished(){
        this.finished = true;
    }

    public void changeEnd_date(Date datee){
        this.end_date = datee;
    }
}
