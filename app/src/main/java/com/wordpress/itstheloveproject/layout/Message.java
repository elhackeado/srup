package com.wordpress.itstheloveproject.layout;

import java.util.Map;

public class Message {

    String messageId;
    String message;
    String user;
    String time;
    String datestamp;

    public Message()
    {

    }

    public Message(String messageId, String message, String user, String time, String datestamp ) {
        this.messageId = messageId;
        this.message = message;
        this.user = user;
        this.time = time;
        this.datestamp = datestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {return user;}

    public String getTime() {return time; }

    public String getDatestamp() {return datestamp; }



}
