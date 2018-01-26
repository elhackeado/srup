package com.wordpress.itstheloveproject.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        getSupportActionBar().hide();
    }

    public void createSession(View view){
        Intent i = new Intent(this, CreateSession.class);
        startActivity(i);
        finish();
    }

    public void joinSession(View view){
        Intent i = new Intent(this, JoinSession.class);
        startActivity(i);
        finish();
    }

}
