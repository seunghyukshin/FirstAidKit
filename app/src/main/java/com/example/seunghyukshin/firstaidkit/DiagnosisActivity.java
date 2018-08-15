package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiagnosisActivity extends AppCompatActivity {
    ListView listView;
    DiagnosisAdapter adapter;
    Button button_complete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        button_complete = (Button) findViewById(R.id.button_complete);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new DiagnosisAdapter();

        adapter.addItem(new DiagnosisContent("머리가 아픈가?",false));
        adapter.addItem(new DiagnosisContent("구토증세가 있다.",false));
        adapter.addItem(new DiagnosisContent("열이 많이난다.",false));
        
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        button_complete.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DiagnosisActivity.this, DiagnosisState.class);

            intent.putExtra("list", adapter.getList());
            startActivity(intent);
        }
        });
    }

    class DiagnosisAdapter extends BaseAdapter {
        ArrayList<DiagnosisContent> items = new ArrayList<DiagnosisContent>();
        ArrayList<Integer> list = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(DiagnosisContent item) {
            items.add(item);
            list.add(new Integer(0));
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public int[] getList() {
            int[] temp = new int[list.size()];
            for(int i = 0; i < list.size(); i++){
                temp[i] = list.get(i).intValue();
            }
            return temp;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            DiagnosisContentView view = new DiagnosisContentView(getApplicationContext());

            DiagnosisContent item = items.get(position);
            view.setContent((position+1) + ". " +  item.getContent());
            view.setCheckbox(item.getCheckbox());

            view.Cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.set(position, b ? new Integer(1) : new Integer(0));
                }
            });

            return view;
        }
    }
}
