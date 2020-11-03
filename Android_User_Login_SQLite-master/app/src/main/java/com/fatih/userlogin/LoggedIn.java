package com.fatih.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoggedIn extends AppCompatActivity {

    Button showUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        showUser = findViewById(R.id.showUsers);

        showUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this,ShowUsers.class);
                startActivity(intent);
            }
        });


    }
}