package com.example.seunghyukshin.firstaidkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FirstAidActivity extends AppCompatActivity {
    ListView listView;
    FirstAidAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        listView = (ListView) findViewById(R.id.list_view);

        adapter = new FirstAidAdapter();
        adapter.addItem(new FirstAidItem("열사병","물을마셔라"));
        adapter.addItem(new FirstAidItem("탈진","병원"));
        adapter.addItem(new FirstAidItem("낙뢰","찌릿"));

        listView.setAdapter(adapter);





    }








    class FirstAidAdapter extends BaseAdapter{
        ArrayList<FirstAidItem> items = new ArrayList<FirstAidItem>();

        @Override
        public int getCount() {
           return items.size();
        }

        public void addItem(FirstAidItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
           return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return  position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            FirstAidItemView view = new FirstAidItemView(getApplicationContext());

            FirstAidItem item = items.get(position);
            view.setName(item.getName());
            view.setContent(item.getContents());
            return view;
        }
    }

}
