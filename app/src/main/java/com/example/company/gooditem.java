package com.example.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class gooditem extends ArrayAdapter {

    ArrayList<HashMap<String,String>> arrayList;
    Context context;
    private int resourceId;

    public gooditem(Context context1, int textViewResourceId, ArrayList<HashMap<String,String>> objects){
        super(context1,textViewResourceId,objects);
        arrayList = objects;
        resourceId = textViewResourceId;
        context = context1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView good = view.findViewById(R.id.GOODNAME);
        TextView count = view.findViewById(R.id.COUNT);
        TextView price = view.findViewById(R.id.PRICE);
        good.setText(arrayList.get(position).get("GoodName"));
        count.setText(arrayList.get(position).get("count"));
        price.setText(arrayList.get(position).get("price"));
        return view;
    }
}