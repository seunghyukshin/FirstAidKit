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

public class FirstAidActivity extends AppCompatActivity {
    TextView TextView_name;
    TextView TextView_contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        TextView_name = (TextView) findViewById(R.id.NameText);
        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
            } else {
                name= extras.getString("NAME");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("NAME");
        }
        TextView_name.setText(name);

        String contents;
        String fileName=name+".txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(getFilesDir()+fileName));
            String readStr = "";
            String str = null;
            while(((str = br.readLine()) != null)){
                readStr += str +"\n";
            }
            br.close();
            Toast.makeText(this, readStr.substring(0, readStr.length()-1), Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "File not Found"+getFilesDir(), Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            e.printStackTrace();
        }


        //TextView_contents.setText();


    }

}
