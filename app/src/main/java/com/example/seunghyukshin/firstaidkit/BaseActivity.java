package com.example.seunghyukshin.firstaidkit;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SEUNGHYUK SHIN on 2018-08-08.
 */

//http://blog.naver.com/PostView.nhn?blogId=hg1286&logNo=220602654734
//글꼴변경

public class BaseActivity extends Activity{
    public Typeface mTypeface = null;

    @Override
    public void setContentView(int layoutResId){
        super.setContentView(layoutResId);
        if(mTypeface == null){
            mTypeface = Typeface.createFromAsset(this.getAssets(),"fonts/BMDOHYEON_ttf.ttf");
        }
        setGlobalFont(getWindow().getDecorView());
    }

    public void setGlobalFont(View view) {
        if(view !=null){
            if(view instanceof ViewGroup){
                ViewGroup vg = (ViewGroup) view;
                int vgCnt = vg.getChildCount();
                for (int i=0;i<vgCnt;i++){
                    View v = vg.getChildAt(i);
                    if(v instanceof TextView){
                        ((TextView) v).setTypeface(mTypeface);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }
}
