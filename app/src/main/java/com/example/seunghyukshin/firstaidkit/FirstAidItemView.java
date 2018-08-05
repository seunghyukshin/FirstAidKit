package com.example.seunghyukshin.firstaidkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by SEUNGHYUK SHIN on 2018-08-02.
 */

public class FirstAidItemView extends LinearLayout{
    TextView textView;
    //TextView textView2;
    public FirstAidItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.first_aid_item,this,true);

        textView = (TextView) findViewById(R.id.textView);
        //textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void setName(String name){
        textView.setText(name);
    }
    /*
    public void setContent(String content){
        textView2.setText(content);
    }
    */
}
