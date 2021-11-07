package com.example.company;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class taskitem extends ArrayAdapter {
    ArrayList<ArrayList<HashMap<String,String>>> arrayList;
    Context context;
    private int resourceId;

    public taskitem(Context context1, int textViewResourceId, ArrayList<ArrayList<HashMap<String,String>>> objects){
        super(context1,textViewResourceId,objects);
        arrayList = objects;
        resourceId = textViewResourceId;
        context = context1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ListView listView = view.findViewById(R.id.listview_taskitem);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        TextView no = view.findViewById(R.id.textvieworderno);
        gooditem gooditem = new gooditem(getContext(),R.layout.activity_gooditem,arrayList.get(position));
        listView.setAdapter(gooditem);
        //no.setText();
        return view;
    }
}
