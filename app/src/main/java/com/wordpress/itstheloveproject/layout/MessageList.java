package com.wordpress.itstheloveproject.layout;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class MessageList extends ArrayAdapter<Message> {


    private Activity context;
    List<Message> messageList;

    public MessageList(Activity context, List<Message> messageList){
        super(context, R.layout.mes, messageList);
        this.context = context;
        this.messageList = messageList;
    }






    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        Message message = messageList.get(position);
        if(message.getUser().equals("aman")) {
        View listViewItem = inflater.inflate(R.layout.mes1, null, true);
        TextView textView = (TextView) listViewItem.findViewById(R.id.textView);
        TextView timeView = (TextView) listViewItem.findViewById(R.id.time);

            textView.setText(message.getMessage());
          timeView.setText(message.getTimestamp());
            return listViewItem;
        }
        else{
            View listViewItem = inflater.inflate(R.layout.mes, null, true);

            TextView textView = (TextView) listViewItem.findViewById(R.id.textView);
            TextView timeView = (TextView) listViewItem.findViewById(R.id.time);

            textView.setText(message.getMessage());
            timeView.setText(message.getTimestamp());
            return listViewItem;
        }



    }

}