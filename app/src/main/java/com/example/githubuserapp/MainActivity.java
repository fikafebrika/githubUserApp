package com.example.githubuserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUsers;
    private ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsers = findViewById(R.id.rv_users);
        rvUsers.setHasFixedSize(true);

        list.addAll(getListUsers());
        showRecyclerList();
    }

    public ArrayList<User> getListUsers() {
        String[] dataUsername = getResources().getStringArray(R.array.data_username);
        String[] dataName = getResources().getStringArray(R.array.data_name);
        TypedArray dataAvatar = getResources().obtainTypedArray(R.array.data_avatar);
        ArrayList<User> listUser = new ArrayList<>();
        for (int i = 0; i < dataUsername.length; i++) {
            User user = new User();
            user.setUsername(dataUsername[i]);
            user.setName(dataName[i]);
            user.setAvatar(dataAvatar.getResourceId(i, -1));
            listUser.add(user);
        }
        return listUser;
    }

    private void showRecyclerList(){
        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            rvUsers.setLayoutManager(new LinearLayoutManager(this));
        }
        ListUserAdapter listUserAdapter = new ListUserAdapter(list);
        rvUsers.setAdapter(listUserAdapter);

        listUserAdapter.setOnItemClickCallback(data -> showSelectedUser(data));
    }

    private void showSelectedUser(User user) {
        Toast.makeText(this, "Kamu memilih " + user.getUsername(), Toast.LENGTH_SHORT).show();
    }
}