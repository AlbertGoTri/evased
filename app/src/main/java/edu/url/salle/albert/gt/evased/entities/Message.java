package edu.url.salle.albert.gt.evased.entities;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {

    //-------------------------------ATTRIBUTES--------------------------------------------------------
    private String content;
    private Date date; //this includes the time and the date
    private User sender;


    //--------------------------------CONSTRUCTORS-----------------------------------------------------
    //constructor if the message is old
    public Message(String content, int currentMilis, User Sender){
        this.date =  new Date(currentMilis);
        this.content = content;
        this.sender = Sender;

    }

    //constructor if the message is new, this way we get the time from the system
    public Message(String content, User Sender){
        this.date = new Date(System.currentTimeMillis());
        this.content = content;
        this.sender = Sender;

    }

    //------------------------------GETTERS------------------------------------------------------------
    public String getContent() {
        return content;
    }

    public String getDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.date);
    }

    public String getTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        return formatter.format(this.date);
    }

    public User getSender() {
        return sender;
    }
    //------------------------------SETTERS------------------------------------------------------------
    //NO NEED FOR THE MOMENT
}
