package edu.url.salle.albert.gt.evased.entities;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {

    //-------------------------------ATTRIBUTES--------------------------------------------------------
    private int id;
    private String content;
    private int user_id_sent;
    private int user_id_recived;
    private String timeStamp;

    public Message(int id, String content, int user_id_sent, int user_id_recived, String timeStamp) {
        this.id = id;
        this.content = content;
        this.user_id_sent = user_id_sent;
        this.user_id_recived = user_id_recived;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id_sent() {
        return user_id_sent;
    }

    public void setUser_id_sent(int user_id_sent) {
        this.user_id_sent = user_id_sent;
    }

    public int getUser_id_recived() {
        return user_id_recived;
    }

    public void setUser_id_recived(int user_id_recived) {
        this.user_id_recived = user_id_recived;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

