package com.example.company;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    HashMap<String, ArrayList<String>> importantdata;
    int l = 1;
    String Floder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "class";
    File directry = new File(Floder);
    File output = new File(directry, "all_class.csv");
    ConnectivityManager CM;//日曆
    NetworkInfo info;//網路狀態
    Button resign_btn, enter;
    TextView visistor;
    List<String> data = new ArrayList<>();//上傳所需物件
    Map<Integer, String> test = new HashMap<>();//上傳所需物件
    Map<String, List> c = new HashMap<>();//上傳所需物件
    long first = 0;//退出指令所需物件
    EditText account, password;
    StringBuilder stringBuilder = new StringBuilder();
    String no;//編號
    int nowvercode;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();//網路資料庫
    boolean ch;//檢查帳號狀態的bool


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        resign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,resign.class);
                startActivity(intent);
            }
        });
    }

    void check() //檢查是否有此帳，並登入
    {


        if (account.getText().toString().equals("")) {
            nofition("你沒有輸入帳號");
            return;
        }
        if (password.getText().toString().equals("")) {
            nofition("你沒有輸入密碼");
            return;
        }
        db.child("store_account").child(account.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map temp = (Map) dataSnapshot.getValue();
                if (temp != null) {
                    String account1, password1;
                    account1 = temp.get("account").toString();
                    password1 = temp.get("password").toString();
                    if (account.getText().toString().equals(account1) && password.getText().toString().equals(password1)) {
                        Intent intent2 = new Intent(MainActivity.this,checkorder.class);
                        System.out.println(temp);
                        intent2.putExtra("storename", temp.get("store").toString());
                        startActivity(intent2);
                        //MainActivity.this.finish();

                    } else
                        nofition("帳號或密碼錯誤");
                } else {
                    nofition("查無此帳");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed()  //雙擊退出事件
    {
        if (System.currentTimeMillis() - first < 2000) {
            super.onBackPressed();
        } else {
            //nofition("再按一次退出");
            first = System.currentTimeMillis();
        }
    }

    void setup()//基本資料設置，變數
    {
        resign_btn = findViewById(R.id.resign_btn);
        enter = findViewById(R.id.enter_main_btn);
        account = findViewById(R.id.account_main_edittext);
        password = findViewById(R.id.password_main_edittext);
        visistor = findViewById(R.id.visitor);
    }
    void nofition(String data)//顯示泡泡資料
    {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

}