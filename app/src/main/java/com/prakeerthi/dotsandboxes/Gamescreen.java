package com.prakeerthi.dotsandboxes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.prakeerthi.dotsandboxes.views.CustomView;

public class Gamescreen extends AppCompatActivity {
      private CustomView customview;
      public TextView player,p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        customview = (CustomView) findViewById(R.id.canvass);
        Intent go=getIntent();
        String txt= go.getStringExtra("size");
        int n= Integer.parseInt(txt);
        customview.size(n);
        player =(TextView) findViewById(R.id.textView);
        p1=(TextView) findViewById(R.id.textView4);
        p2=(TextView) findViewById(R.id.textView5);
        customview.change(player,p1,p2);





    }



}
