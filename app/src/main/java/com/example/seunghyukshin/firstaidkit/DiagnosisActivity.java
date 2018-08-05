package com.example.seunghyukshin.firstaidkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class DiagnosisActivity extends AppCompatActivity {
    ListView listView;
    DiagnosisAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new DiagnosisAdapter();

        adapter.addItem(new DiagnosisContent("머리가 아픈가?",false));
        listView.setAdapter(adapter);
    }
    class DiagnosisAdapter extends BaseAdapter {
        ArrayList<DiagnosisContent> items = new ArrayList<DiagnosisContent>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(DiagnosisContent item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            DiagnosisContentView view = new DiagnosisContentView(getApplicationContext());

            DiagnosisContent item = items.get(position);
            view.setContent(item.getContent());
            view.setCheckbox(item.getCheckbox());
            return view;
        }
    }
}
