package com.wordpress.itstheloveproject.layout;



import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    EditText editTextMessage;
    ImageView buttonSend;
    ListView listViewMessages;
    List<Message> messageList;



    DatabaseReference databaseChat;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle item selection
        switch (item.getItemId()) {
            case R.menu.lock:
                    lock();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void lock(){

        Intent intent = new Intent(MainActivity.this, lock.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);




        databaseChat = FirebaseDatabase.getInstance().getReference("chat");


        editTextMessage = (EditText) findViewById(R.id.editTextName);
        buttonSend = (ImageView) findViewById(R.id.buttonAddArtist);

        listViewMessages = (ListView) findViewById(R.id.list_view);
        messageList = new ArrayList<>();

        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addMessage();
                editTextMessage.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myConnectionsRef = database.getReference("users/Aman/online");

        final DatabaseReference lastOnlineRef = database.getReference("/users/Aman");

        lastOnlineRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String online = dataSnapshot.child("online").getValue().toString();
                String lastonline = dataSnapshot.child("lastOnline").getValue().toString();
                if(online.equals("true")){
                    android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle("Srup");
                    actionBar.setSubtitle(Html.fromHtml("<font color='#FFFFFFFF'> Online </font>"));
                }
                else {
                    android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle("Srup");
                    actionBar.setSubtitle(Html.fromHtml("<font color='#FFFFFFFF'> " + lastonline + "</font>"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        final DatabaseReference connectedRef = database.getReference(".info/connected");

        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {


                    lastOnlineRef.child("lastOnline").onDisconnect().setValue(ServerValue.TIMESTAMP);



                    myConnectionsRef.setValue(Boolean.TRUE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled at .info/connected");
            }
        });


        databaseChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //addNotification();
                messageList.clear();;


                for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                    Message message = messageSnapshot.getValue(Message.class);

                    messageList.add(message);
                }

                MessageList adapter = new MessageList(MainActivity.this, messageList);
                listViewMessages.setAdapter(adapter);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void addMessage(){
        String message = editTextMessage.getText().toString().trim();
        String user = "aman";




       // Object time = ServerValue.TIMESTAMP;


        String time = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
        String dateStamp =java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());


        if(!TextUtils.isEmpty(message)){

            String messageId = databaseChat.push().getKey();
            com.wordpress.itstheloveproject.layout.Message obj = new com.wordpress.itstheloveproject.layout.Message(messageId, message, user, time, dateStamp);
            databaseChat.child(messageId).setValue(obj);


            Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "you should enter a Message", Toast.LENGTH_LONG).show();
        }





    }


    private void addNotification() {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.gitc)
                        .setContentTitle("Srup")
                        .setContentText("This is a test notification");

        //int numMessages=1;
        //builder.setNumber(++numMessages);
        Intent notificationIntent = new Intent(this, NotificationView.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }

    @Override
    protected void onPause() {

        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myConnectionsRef = database.getReference("users/Aman/online");

        myConnectionsRef.setValue(Boolean.FALSE);

        final DatabaseReference lastOnlineRef = database.getReference("/users/Aman/lastOnline");
        lastOnlineRef.setValue(ServerValue.TIMESTAMP);

    }


    }

