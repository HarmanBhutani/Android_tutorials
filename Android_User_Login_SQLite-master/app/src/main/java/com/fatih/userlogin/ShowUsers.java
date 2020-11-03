package com.fatih.userlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowUsers extends AppCompatActivity {

    ListView showUser;
    ArrayAdapter userArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        showUser = findViewById(R.id.userList);

        DataBase dataBase = new DataBase(ShowUsers.this);

        List<UserModel> everyUser = dataBase.showAllUsers();

        userArrayAdapter = new ArrayAdapter<UserModel>(ShowUsers.this, android.R.layout.simple_list_item_1,everyUser);

        showUser.setAdapter(userArrayAdapter);

        showUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataBase dataBase = new DataBase(ShowUsers.this);

                UserModel clickedUser = (UserModel) parent.getItemAtPosition(position);

                dataBase.deleteUser(clickedUser);

                Toast.makeText(ShowUsers.this, "Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }
}