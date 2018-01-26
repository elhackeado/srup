package com.wordpress.itstheloveproject.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SuperMainActivity extends AppCompatActivity {

    TextView textViewQuestion;
    EditText editTextAnswer;
    Button buttonUnlock;
    DatabaseReference questionRef;
    String question;
    String answer1;
    String answer2;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.super_main_activity);

        questionRef = FirebaseDatabase.getInstance().getReference("question/srup");

         questionRef.addValueEventListener(new ValueEventListener() {
           @Override
         public void onDataChange(DataSnapshot dataSnapshot) {

               question = dataSnapshot.child("question").getValue().toString();
               answer1 = dataSnapshot.child("answer1").getValue().toString();
               answer2 = dataSnapshot.child("answer2").getValue().toString();
               if(answer1.equals(answer2))
               {
                   Intent intent = new Intent(SuperMainActivity.this, One_time.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
                   finish();
               }
               textViewQuestion.setText(question);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

             }
        }
        );

        buttonUnlock = (Button) findViewById(R.id.unlock);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewQuestion.setText(question);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);

    }



    /** Called when the user taps the Unlock button */
    public void unlock(View view) {
        Intent intent = new Intent(SuperMainActivity.this, MainActivity.class);
        EditText editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        String message = editTextAnswer.getText().toString().toUpperCase();
        if(message.equals(answer1)) {
            questionRef.child("answer2").setValue(answer1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else{
        Toast.makeText(this, "Wrong Answer", Toast.LENGTH_LONG).show();
        }

    }
}