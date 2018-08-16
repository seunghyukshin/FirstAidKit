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


        firstAidMap.put("골절","뼈가 부러지셧다고요? 119에 전화하세요!");
        firstAidMap.put("기도폐쇄","기도가 막히셨다고요?");
        firstAidMap.put("뇌수막염","");
        firstAidMap.put("뇌졸중","");
        firstAidMap.put("당뇨 응급상황","");
        firstAidMap.put("머리손상","");
        firstAidMap.put("무의식","");
        firstAidMap.put("발작/간질","");
        firstAidMap.put("심리적 응급처치","");
        firstAidMap.put("심장발작","");
        firstAidMap.put("쏘임/물림","");
        firstAidMap.put("알레르기/아나필락시스","");
        firstAidMap.put("열사병","");
        firstAidMap.put("저체온증","");
        firstAidMap.put("정신적 고통","");
        firstAidMap.put("좌상/염좌","");
        firstAidMap.put("중독/해로운 물질","");
        firstAidMap.put("천식발작","");
        firstAidMap.put("출혈","");
        firstAidMap.put("화상","");

        TextView_contents.setText(firstAidMap.get(name));
    }


}
