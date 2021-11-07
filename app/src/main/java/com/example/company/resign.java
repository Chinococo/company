package com.example.company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class resign extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    EditText storename, account, nickname, password, no, truename,repeat;
    Button enter;
    long first = 0;//退出指令所需物件
    HashMap<String, String> worker = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) //main()
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);
        setup();
        event();
    }

    void input_data()//設置所輸入資料
    {
        worker.put("store", storename.getText().toString());
        worker.put("account", account.getText().toString());
        worker.put("password", password.getText().toString());
    }

    void nofition(String data)//顯示泡泡資訊
    {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    void setup() //基本設定，變數
    {
        password = findViewById(R.id.EditText_password);
        account = findViewById(R.id.EdittextNewItemprice);
        enter = findViewById(R.id.btn_addnewItem);
        storename = findViewById(R.id.EdittextNewItemName);
        repeat = findViewById(R.id.editText_repeat);
    }

    void check()//檢查所有是否輸入正確
    {

        if (storename.getText().toString().equals("")) {
            nofition("你的店名沒輸入喔 ");
            return;
        }
        if (account.getText().toString().equals("")) {
            nofition("你的帳號沒輸入喔 ");
            return;
        }
        if (password.getText().toString().equals("")) {
            nofition("你的密碼沒輸入喔 ");
            return;
        }
        if (!password.getText().toString().equals(repeat.getText().toString())) {
            nofition("你的密碼不相同 ");
            return;
        }
        reference.child("store_account").child(account.getText().toString()).child("account").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                input_data();
                String temp = dataSnapshot.getValue(String.class);
                if (temp == null) {
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("store_account").child(account.getText().toString()).getValue() == null) {
                                reference.child("store_account").child(account.getText().toString()).setValue(worker);
                                ArrayList<String> t;
                                if(dataSnapshot.child("Allrestaurant").getValue()==null)
                                    t = new ArrayList<>();
                                else
                                    t = (ArrayList<String>) dataSnapshot.child("Allrestaurant").getValue();
                                t.add(storename.getText().toString());
                                reference.child("Allrestaurant").setValue(t);

                                nofition("successful");
                                Intent intent = new Intent(resign.this, MainActivity.class);
                                startActivity(intent);
                                //resign.this.finish();
                            } else {
                                Log.e("end", "end");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    nofition(temp + "此帳已被註冊");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    void event()//all listner
    {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_data();
                check();
            }

        });
    }

    @Override
    public void onBackPressed()  //退出事件
    {
        if (System.currentTimeMillis() - first < 2000) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            nofition("再按一次退出");
            first = System.currentTimeMillis();
        }
    }
}