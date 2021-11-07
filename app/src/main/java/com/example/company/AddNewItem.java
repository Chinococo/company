package com.example.company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AddNewItem extends AppCompatActivity {
    EditText itemName, itemprice;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        itemName = findViewById(R.id.EdittextNewItemName);
        itemprice = findViewById(R.id.EdittextNewItemprice);
        btn = findViewById(R.id.btn_addnewItem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item item = new item(null, itemName.getText().toString(), Integer.parseInt(itemprice.getText().toString()));
                if (!itemName.equals("") && !itemprice.equals("")) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("menu").child(getIntent().getStringExtra("storename")).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ArrayList<HashMap<String, String>> data;
                            if (snapshot.getValue() == null) {
                                data = new ArrayList<>();
                                HashMap<String, String> t= new HashMap<>();
                                t.put("itemName",itemName.getText().toString());
                                t.put("itemPrice",itemprice.getText().toString());
                                data.add(t);
                                reference.child("menu").child(getIntent().getStringExtra("storename")).setValue(data);
                            } else {
                                data = (ArrayList<HashMap<String, String>>) snapshot.getValue();
                                boolean b = true;
                                for(int i=0;i<data.size();i++)
                                {
                                    if(data.get(i).get("itemName").equals(itemName.getText().toString()))
                                    b=false;
                                }
                                if(b)
                                {
                                    HashMap<String, String> t= new HashMap<>();
                                    t.put("itemName",itemName.getText().toString());
                                    t.put("itemPrice",itemprice.getText().toString());
                                    data.add(t);
                                    reference.child("menu").child(getIntent().getStringExtra("storename")).setValue(data);
                                    itemName.setText("");
                                    itemprice.setText("");
                                }else
                                {
                                    Toast.makeText(AddNewItem.this,"此項目已經存在",Toast.LENGTH_LONG).show();
                                }

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}