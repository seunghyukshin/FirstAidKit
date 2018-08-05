package com.example.seunghyukshin.firstaidkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FirstAidActivity extends AppCompatActivity {
    ListView listView_today_fa; //오늘의 응급상황
    ListView listView_fa;//응급처치법 쭈루룩
    FirstAidAdapter adapter_fa;
    FirstAidAdapter adapter_today_fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);

        listView_today_fa = (ListView) findViewById(R.id.listView);
        adapter_today_fa = new FirstAidAdapter();
        // 우선순위 알고리즘추가 할것
        adapter_today_fa.addItem(new FirstAidItem("열사병"));
        adapter_today_fa.addItem(new FirstAidItem("식중독"));

        listView_today_fa.setAdapter(adapter_today_fa);





        listView_fa = (ListView) findViewById(R.id.listView2);
        //가나다 순으로
        adapter_fa = new FirstAidAdapter();
        adapter_fa.addItem(new FirstAidItem("골절"));
        adapter_fa.addItem(new FirstAidItem("기도폐쇄"));
        adapter_fa.addItem(new FirstAidItem("뇌수막염"));
        adapter_fa.addItem(new FirstAidItem("뇌졸중"));
        adapter_fa.addItem(new FirstAidItem("당뇨 응급상황"));
        adapter_fa.addItem(new FirstAidItem("머리손상"));
        adapter_fa.addItem(new FirstAidItem("무의식"));
        adapter_fa.addItem(new FirstAidItem("발작/간질"));
        adapter_fa.addItem(new FirstAidItem("심리적 응급처치"));
        adapter_fa.addItem(new FirstAidItem("심장발작"));
        adapter_fa.addItem(new FirstAidItem("쏘임/물림"));
        adapter_fa.addItem(new FirstAidItem("알레르기/아나필락시스"));
        adapter_fa.addItem(new FirstAidItem("열사병"));
        adapter_fa.addItem(new FirstAidItem("저체온증"));
        adapter_fa.addItem(new FirstAidItem("정신적 고통"));
        adapter_fa.addItem(new FirstAidItem("좌상/염좌"));
        adapter_fa.addItem(new FirstAidItem("중독/해로운 물질"));
        adapter_fa.addItem(new FirstAidItem("천식발작"));
        adapter_fa.addItem(new FirstAidItem("출혈"));
        adapter_fa.addItem(new FirstAidItem("화상"));

        listView_fa.setAdapter(adapter_fa);





    }




    //오늘의 응급상황




    //응급처치법
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
            //view.setContent(item.getContents());
            return view;
        }
    }

}
