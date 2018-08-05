package com.example.seunghyukshin.firstaidkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstAidItem {

    String name;
    //String contents;
    public FirstAidItem(String name){
        this.name = name;
    }
    /*
    public FirstAidItem(String name,String contents){
        this.name= name;
        this.contents= contents;
    }*/

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    /*
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getContents() {
        return contents;
    }
*/
}
