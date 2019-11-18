package com.example.birdwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText enterBirdName, enterPersonName, enterZipCode;
    Button buttonSubmit, buttonToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBirdName = findViewById(R.id.enterBirdName);
        enterPersonName = findViewById(R.id.enterPersonName);
        enterZipCode = findViewById(R.id.enterZipCode);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonToSearch = findViewById(R.id.buttonToSearch);

        buttonSubmit.setOnClickListener(this);
        buttonToSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bird");

        if(buttonSubmit==v){
            String createBird = enterBirdName.getText().toString();
            int createZip = Integer.parseInt(enterZipCode.getText().toString());
            String createSighter = enterPersonName.getText().toString();

            Bird myBird = new Bird(createBird, createZip, createSighter);
            myRef.push().setValue(myBird);
        } else if (buttonToSearch==v) {
            Intent mainIntent= new Intent(MainActivity.this, SearchActivity.class);
            startActivity(mainIntent);

        }
    }
}
