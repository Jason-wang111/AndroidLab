package com.cst2335.androidexamples;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText email;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);//loads layout

        prefs = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String savedString = prefs.getString("Email address", "");

        email = findViewById(R.id.email);
        email.setText(savedString);
       // email.setText(getSharedPreferences("profile", Context.MODE_PRIVATE).getString("Email address", ""));

        //Button login go in to profile page
        Intent pf = new Intent(this, ProfileActivity.class);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(bt -> {
            pf.putExtra("Email address", email.getText().toString());
            startActivity(pf);
        });


    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = prefs.edit();
        String typed = email.getText().toString();
        editor.putString("Email address", typed);
        editor.commit();
    }

}