package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DiagnosisState extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_diagnosis);

        Intent intent = getIntent();

        int[] list = intent.getIntArrayExtra("list");

        Toast.makeText(getApplicationContext(), list[0] + ", " + list[1] + ", " + list[2], Toast.LENGTH_SHORT ).show();
    }
}
