package com.example.birdwatchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextFindZip;
    TextView textViewBirdName, textViewSighterName;
    Button buttonSearch, buttonToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextFindZip = findViewById(R.id.editTextFindZip);

        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewSighterName = findViewById(R.id.textViewSighterName);

        buttonSearch = findViewById(R.id.buttonSearch);
        buttonToMain = findViewById(R.id.buttontoMain);

        buttonSearch.setOnClickListener(this);
        buttonToMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bird");

        if (buttonSearch==v){
            int findZip = Integer.parseInt(editTextFindZip.getText().toString());

            myRef.orderByChild("zipCode").equalTo(findZip).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);

                    String findBirdName = foundBird.birdName;
                    String findSighterName = foundBird.sighterName;

                    textViewBirdName.setText(findBirdName);
                    textViewSighterName.setText(findSighterName);



                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if (buttonToMain==v){
            Intent mainIntent= new Intent(SearchActivity.this, MainActivity.class);
            startActivity(mainIntent);
        }


    }
}
