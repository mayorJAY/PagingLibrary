package com.example.josycom.paginglibrary.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.josycom.paginglibrary.R;
import com.example.josycom.paginglibrary.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        final UserAdapter userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel itemViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        itemViewModel.userPagedList.observe(this, userAdapter::submitList);
        recyclerView.setAdapter(userAdapter);
    }
}
