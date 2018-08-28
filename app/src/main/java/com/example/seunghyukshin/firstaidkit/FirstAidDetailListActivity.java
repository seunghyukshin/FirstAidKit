package com.example.seunghyukshin.firstaidkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstAidDetailListActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    Button button;

    FirstAidAdapter adapter_fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_detail_list);

        textView = findViewById(R.id.first_aid_name);
        listView = findViewById(R.id.listView3);
        button = findViewById(R.id.button_first_aid_detail_cancel);

        adapter_fa = new FirstAidAdapter();

        Bundle extras = getIntent().getExtras();
        String first_aid_name = extras.getString("NAME");
        textView.setText(first_aid_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        listView.setAdapter(adapter_fa);

        if(first_aid_name.equals("골절")){
            adapter_fa.addItem(new FirstAidListItem(R.drawable.fa_0,"척추 골절"));
            adapter_fa.addItem(new FirstAidListItem(R.drawable.fa_1,"팔 골절"));
            adapter_fa.addItem(new FirstAidListItem(R.drawable.fa_2,"골반 골절"));
            adapter_fa.addItem(new FirstAidListItem(R.drawable.fa_3,"발 골절"));
            adapter_fa.addItem(new FirstAidListItem(R.drawable.fa_4,"쇄골 골절"));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirstAidListItem item= (FirstAidListItem) adapter_fa.items.get(position);//선택한 병

                Intent intent;
                String name = item.getName();

                intent = new Intent( FirstAidDetailListActivity.this, FirstAidActivity.class);

                intent.putExtra("NAME",name);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    class FirstAidAdapter extends BaseAdapter {
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
            FirstAidListItemView2 view = new FirstAidListItemView2(getApplicationContext());

            FirstAidListItem item = items.get(position);
            view.setName(item.getName());
            view.setImage(item.getImage());
            return view;

        }
    }
}
