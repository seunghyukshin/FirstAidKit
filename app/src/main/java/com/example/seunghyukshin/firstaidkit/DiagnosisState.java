package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DiagnosisState extends AppCompatActivity {

    TextView textView_symptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_diagnosis);

        Intent intent = getIntent();

        int[] list = intent.getIntArrayExtra("list");

        Toast.makeText(getApplicationContext(), list[0] + ", " + list[1] + ", " + list[2], Toast.LENGTH_SHORT).show();

        textView_symptom = (TextView) findViewById(R.id.symptom);

        String symptom="";
        for (int i = 0; i < list.length; i++) {
            if (list[0] == 1) {
                symptom = "머리손상";
            }
            if (list[1] == 1 || list[2] == 1 || list[3] == 1 || list[4] == 1) {
                symptom = "열사병";
            }
        }
        textView_symptom.setText(symptom);
    }
}
