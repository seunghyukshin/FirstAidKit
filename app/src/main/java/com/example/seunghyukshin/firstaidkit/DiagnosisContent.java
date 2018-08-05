package com.example.seunghyukshin.firstaidkit;

import android.widget.CheckBox;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiagnosisContent {
    String Content;
    Boolean Checked;

    public DiagnosisContent(String Content, Boolean Checked) {
        this.Content = Content;
        this.Checked = Checked;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setCheckbox(Boolean Checked) {
        this.Checked = Checked;
    }

    public String getContent() {
        return Content;
    }

    public Boolean getCheckbox() {
        return Checked;
    }
}
