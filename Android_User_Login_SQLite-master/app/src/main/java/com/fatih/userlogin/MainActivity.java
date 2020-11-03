package com.fatih.userlogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    EditText userName,userPassword;
    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.logInButton);
        register = findViewById(R.id.logInRegister);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.password);
        infoText = findViewById(R.id.info);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String name = userName.getText().toString();
                    String password = userPassword.getText().toString();
                    DataBase dataBase = new DataBase(MainActivity.this);
                    System.out.println(dataBase.checkLogin(name,password));
                    if(dataBase.checkLogin(name,password)){
                        /*
                        *Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                            startActivity(i);
                        */

                        Intent intent = new Intent(MainActivity.this,LoggedIn.class);
                        startActivity(intent);
                    }else{
                        infoText.setText("User name or password is incorrect");
                        infoText.setTextColor(Color.RED);

                    }
                }catch (Exception e){

                    Toast.makeText(MainActivity.this, "SomeThing Goes Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });




    }


}