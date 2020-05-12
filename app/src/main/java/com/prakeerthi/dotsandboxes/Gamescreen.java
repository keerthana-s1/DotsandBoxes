package com.prakeerthi.dotsandboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.prakeerthi.dotsandboxes.views.CustomView;

public class Gamescreen extends AppCompatActivity {
      private CustomView customview;
      private View newl;
      public TextView player;
      public int pl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        customview = (CustomView) findViewById(R.id.canvass);
        newl =(View) findViewById(R.id.view2);

        player =(TextView) findViewById(R.id.textView);
         disp();
    }
    public void disp()
    {   pl= customview.getpl();
        player.setText(String.valueOf(pl));

    }


}
