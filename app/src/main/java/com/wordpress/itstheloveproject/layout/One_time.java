package com.wordpress.itstheloveproject.layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class One_time extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time);

    }

    public void submituserdata(View view){
        EditText questionText = (EditText) findViewById(R.id.username);
        String username = questionText.getText().toString().toUpperCase();
        // We need an editor object to make changes
        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

    // Set/Store data
        edit.putString("username", username);
        edit.putBoolean("logged_in", true);

    // Commit the changes
        edit.commit();



        Toast.makeText(this, pref.getString("username", "") , Toast.LENGTH_LONG).show();



    }
}
