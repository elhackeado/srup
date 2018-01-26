package com.wordpress.itstheloveproject.layout;

import android.text.Html;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Message {

    String messageId;
    String message;
    String user;
    String timestamp;
    //String time;
    //String datestamp;

    public Message()
    {

    }

    public Message(String messageId, String message, String user, String timestamp ) {
        this.messageId = messageId;
        this.message = message;
        this.user = user;
        this.timestamp = timestamp;

    }

    //public String getMessageId() {return messageId;}

    public String getMessage() {
        return message;
    }

    public String getUser() {return user;}

    public String getTimestamp() {return timestamp; }

    //public String getDatestamp() {return datestamp; }



}
