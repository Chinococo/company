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

public class online extends AppCompatActivity {
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("tasks");
    String storeName;
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        storeName = getIntent().getStringExtra("storename");
        listView = findViewById(R.id.taskListview);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, data);

        db.child(storeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = (ArrayList<String>) snapshot.getValue();
                update();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    void update() {
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, data);
    }
}