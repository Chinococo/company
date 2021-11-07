package com.example.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class checkorder extends AppCompatActivity {
    ImageView add,online;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkorder);
        add = findViewById(R.id.add_item);
        online = findViewById(R.id.online);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(checkorder.this,AddNewItem.class);
                intent.putExtra("storename",getIntent().getExtras().getString("storename"));
                startActivity(intent);
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(checkorder.this,online.class);
                intent.putExtra("storename",getIntent().getExtras().getString("storename"));
                startActivity(intent);
            }
        });
    }
}