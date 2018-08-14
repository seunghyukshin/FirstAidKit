package com.example.seunghyukshin.firstaidkit;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by SEUNGHYUK SHIN on 2018-08-02.
 */

public class FirstAidListItemView extends LinearLayout{
    TextView textView;
    //TextView textView2;
    BaseActivity ba;
    public FirstAidListItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.first_aid_list_item,this,true);
        ba=new BaseActivity();
        textView = (TextView) findViewById(R.id.textView);
        //textView.setTypeface(Typeface.createFromAsset( /*ba.getAssets()*/, "fonts/BMDOHYEON_ttf.ttf"));
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
