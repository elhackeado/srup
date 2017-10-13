package com.wordpress.itstheloveproject.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class lock extends AppCompatActivity {


    DatabaseReference databaseQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        getSupportActionBar().hide();


        databaseQuestion = FirebaseDatabase.getInstance().getReference("question/aman");
        databaseQuestion.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                 EditText questionText = (EditText) findViewById(R.id.yourquestionedittext);
                 EditText answerText = (EditText) findViewById(R.id.youransweredittext);
                 questionText.setText(dataSnapshot.child("question").getValue().toString());
                 answerText.setText(dataSnapshot.child("answer1").getValue().toString());

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

    }

    public void lockher(View view) {

        databaseQuestion = FirebaseDatabase.getInstance().getReference("question/aman");
        EditText questionText = (EditText) findViewById(R.id.yourquestionedittext);
        EditText answerText = (EditText) findViewById(R.id.youransweredittext);
        String question = questionText.getText().toString().toUpperCase();
        String answer = answerText.getText().toString().toUpperCase();

        if (question.equals("")) {
            Toast.makeText(this, "Question field is left blank", Toast.LENGTH_LONG).show();
        }
        if (answer.equals("")) {
            Toast.makeText(this, "Every question must have an answer", Toast.LENGTH_LONG).show();
        }
        if (!question.equals("") && !answer.equals("")) {

            databaseQuestion.child("question").setValue(question);
            databaseQuestion.child("answer1").setValue(answer);
            databaseQuestion.child("answer2").setValue("");
            Toast.makeText(this, "LOCKED SUCCESSFULLY", Toast.LENGTH_LONG).show();
        }
    }

    public void unlockher(View view) {
        databaseQuestion = FirebaseDatabase.getInstance().getReference("question/aman");
        databaseQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                databaseQuestion.child("answer2").setValue(dataSnapshot.child("answer1").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

                Toast.makeText(this, "UNLOCKED SUCCESSFULLY", Toast.LENGTH_LONG).show();
    }
}
