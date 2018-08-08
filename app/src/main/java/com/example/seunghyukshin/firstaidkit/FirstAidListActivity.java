package com.example.seunghyukshin.firstaidkit;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
public class FirstAidListActivity extends BaseActivity {
    ListView listView_today_fa; //오늘의 응급상황
    ListView listView_fa;//응급처치법 쭈루룩
    FirstAidAdapter adapter_fa;
    FirstAidAdapter adapter_today_fa;
    LinearLayout layout_fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_list);

        //배경화면색
        layout_fa = (LinearLayout) findViewById(R.id.FirstAidListLayout);
        layout_fa.setBackgroundColor(Color.rgb(200,191,231));

        listView_today_fa = (ListView) findViewById(R.id.listView);
        adapter_today_fa = new FirstAidAdapter();
        // 우선순위 알고리즘추가 할것
        adapter_today_fa.addItem(new FirstAidListItem("열사병"));

        listView_today_fa.setAdapter(adapter_today_fa);





        listView_fa = (ListView) findViewById(R.id.listView2);
        //가나다 순으로
        adapter_fa = new FirstAidAdapter();
        adapter_fa.addItem(new FirstAidListItem("골절"));
        adapter_fa.addItem(new FirstAidListItem("기도폐쇄"));
        adapter_fa.addItem(new FirstAidListItem("뇌수막염"));
        adapter_fa.addItem(new FirstAidListItem("뇌졸중"));
        adapter_fa.addItem(new FirstAidListItem("당뇨 응급상황"));
        adapter_fa.addItem(new FirstAidListItem("머리손상"));
        adapter_fa.addItem(new FirstAidListItem("무의식"));
        adapter_fa.addItem(new FirstAidListItem("발작/간질"));
        adapter_fa.addItem(new FirstAidListItem("심리적 응급처치"));
        adapter_fa.addItem(new FirstAidListItem("심장발작"));
        adapter_fa.addItem(new FirstAidListItem("쏘임/물림"));
        adapter_fa.addItem(new FirstAidListItem("알레르기/아나필락시스"));
        adapter_fa.addItem(new FirstAidListItem("열사병"));
        adapter_fa.addItem(new FirstAidListItem("저체온증"));
        adapter_fa.addItem(new FirstAidListItem("정신적 고통"));
        adapter_fa.addItem(new FirstAidListItem("좌상/염좌"));
        adapter_fa.addItem(new FirstAidListItem("중독/해로운 물질"));
        adapter_fa.addItem(new FirstAidListItem("천식발작"));
        adapter_fa.addItem(new FirstAidListItem("출혈"));
        adapter_fa.addItem(new FirstAidListItem("화상"));

        listView_fa.setAdapter(adapter_fa);

        listView_fa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FirstAidListItem item = (FirstAidListItem) adapter_fa.getItem(position);

                //Intent intent = new Intent( FirstAidListActivity.class, FirstAidListActivity.class);
                // startActivity(intent);
            }
        });




    }




    class FirstAidAdapter extends BaseAdapter{
        ArrayList<FirstAidListItem> items = new ArrayList<FirstAidListItem>();

        @Override
        public int getCount() {
           return items.size();
        }

        public void addItem(FirstAidListItem item){
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
            FirstAidListItemView view = new FirstAidListItemView(getApplicationContext());

            FirstAidListItem item = items.get(position);
            view.setName(item.getName());
            //view.setContent(item.getContents());

//            if (convertView == null) {
//                view.setTypeface(Typeface.createFromAsset(convertView.getContext().getAssets(), "fonts/NanumBarunGothicBold.ttf"));
//            }
//            return convertView;
            return view;

        }
    }

}
