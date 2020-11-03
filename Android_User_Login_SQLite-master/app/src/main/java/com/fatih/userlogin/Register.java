package com.fatih.userlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText registername, registerpassword, registerpasswordsecond;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registername = findViewById(R.id.registerName);
        registerpassword = findViewById(R.id.registerPassword);
        registerpasswordsecond = findViewById(R.id.registerPasswordSecond);
        add = findViewById(R.id.registerAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel = null;

                System.out.println("First :"+registerpassword.getText().toString()+"Second :"+registerpasswordsecond.getText().toString());

                    if(registerpassword.getText().toString().equals(registerpasswordsecond.getText().toString())) {
                        userModel = new UserModel(-1, registername.getText().toString(), registerpassword.getText().toString());
                        Toast.makeText(Register.this, userModel.toString(), Toast.LENGTH_LONG).show();

                        DataBase dataBase = new DataBase(Register.this);

                        dataBase.addUser(userModel);
                    }else {

                        Toast.makeText(Register.this, "PASSWORDS AREN'T THE SAME", Toast.LENGTH_SHORT).show();
                        userModel = new UserModel(-1, "Error", "");
                    }




                
            }
        });

    }
}