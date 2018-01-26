package com.wordpress.itstheloveproject.layout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class stamp {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference ref = database.getReference("chat2");
    public String messageId;
    public String message;
    public String user;
    String timestamp;


    public stamp(String messageId, String message, String user, String timestamp) {
        this.messageId=messageId;
        this.message = message;
        this.user = user;
        this.timestamp = timestamp;
        ss();
    }


    public void ss()

    {

        Map<String, stamp> stamps = new HashMap<>();
        stamps.put(messageId, new stamp(messageId, message, user, timestamp));
        ref.setValue(stamps);


    }
}