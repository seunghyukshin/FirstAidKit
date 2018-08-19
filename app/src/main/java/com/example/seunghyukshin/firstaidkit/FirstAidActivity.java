package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstAidActivity extends AppCompatActivity {
    TextView TextView_name;
    TextView TextView_contents;
    HashMap<String,String> firstAidMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        TextView_name = (TextView) findViewById(R.id.NameText);
        TextView_contents = (TextView) findViewById(R.id.ContentsText);
        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
            } else {
                name= extras.getString("NAME");
            }
        }else {
            name= (String) savedInstanceState.getSerializable("NAME");
        }
        TextView_name.setText(name);
        setFirstAidMap();
        TextView_contents.setText(firstAidMap.get(name));
    }


    private void setFirstAidMap(){
        firstAidMap.put("fa_0","뼈가 부러지셧다고요? 119에 전화하세요!");
        firstAidMap.put("fa_1","기도가 막히셨다고요?");
        firstAidMap.put("fa_2","");
        firstAidMap.put("fa_3","");
        firstAidMap.put("fa_4 응급상황","");
        firstAidMap.put("fa_5","");
        firstAidMap.put("fa_6","");
        firstAidMap.put("발작/간질","");
        firstAidMap.put("심리적 응급처치","");
        firstAidMap.put("fa_9","");
        firstAidMap.put("쏘임/물림","");
        firstAidMap.put("알레르기/아나필락시스","");
        firstAidMap.put("fa_12","");
        firstAidMap.put("fa_13","");
        firstAidMap.put("정신적 고통","");
        firstAidMap.put("좌상/염좌","");
        firstAidMap.put("중독/해로운 물질","");
        firstAidMap.put("천식발작","");
        firstAidMap.put("fa_18","");
        firstAidMap.put("fa_19","");
    }

}
