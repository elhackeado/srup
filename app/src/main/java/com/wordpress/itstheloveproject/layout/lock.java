package com.wordpress.itstheloveproject.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class lock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        getSupportActionBar().hide();
    }
}
