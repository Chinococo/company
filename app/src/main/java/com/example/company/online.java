package com.example.company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class online extends AppCompatActivity {
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    String storeName;
    ListView listView;
    ArrayList<ArrayList<HashMap<String,String>>> data = new ArrayList<>();
    taskitem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        storeName = getIntent().getStringExtra("storename");
        listView = findViewById(R.id.Listview_online);

        db.child("Task").child(storeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = (ArrayList<ArrayList<HashMap<String,String>>>) snapshot.getValue();
                adapter = new taskitem(online.this,R.layout.taskitem,data);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}