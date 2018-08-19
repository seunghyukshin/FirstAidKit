package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DiagnosisState extends AppCompatActivity {

    TextView textView_symptom;
    TextView lastline;
    Button button_go_to_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_diagnosis);

        Intent intent = getIntent();

        int[] list = intent.getIntArrayExtra("list");

        Toast.makeText(getApplicationContext(), list[0] + ", " + list[1] + ", " + list[2], Toast.LENGTH_SHORT).show();

        button_go_to_home = (Button) findViewById(R.id.Gotohome);
        textView_symptom = (TextView) findViewById(R.id.symptom);
        lastline = (TextView) findViewById(R.id.last_text);

        String symptom = "";
        String Lastline = "으로 예상됩니다.";
        for (int i = 0; i < list.length; i++) {
            if (list[0] == 1 && list[9] == 1) {
                symptom = "머리손상";
            } else if (list[14] == 1 && list[15] == 1 && list[0] == 1 || list[9] == 1 && list[1] == 1) {
                symptom = "아나필락시스(알레르기증상)";
            } else if (list[0] == 1 && list[2] == 1 && list[6] == 1) {
                symptom = "뇌수막염";
            } else if (list[2] == 1 && list[1] == 1 || list[4] == 1 || list[9] == 1 && list[3] == 1) {
                symptom = "열사병";
            } else if (list[3] == 1 && list[8] == 1 || list[6] == 1) {
                symptom = "저체온증";
            } else if (list[10] == 1 && list[0] == 1 || list[1] == 1 || list[9] == 1 && list[11] == 1 || list[12] == 1) {
                symptom = "뇌졸중";
            } else if (list[5] == 1 && list[13] == 1) {
                symptom = "심장질환";
            } else {
                symptom = "증상을 알 수 없습니다! 가까운 병원을 방문해주세요.";
                Lastline = "";
            }
        }
        textView_symptom.setText(symptom);
        lastline.setText(Lastline);

        button_go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiagnosisState.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
