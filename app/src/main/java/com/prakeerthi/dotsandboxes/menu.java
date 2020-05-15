package com.prakeerthi.dotsandboxes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
     private Button medium,small,large;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        medium= (Button) findViewById(R.id.button2);
        small=(Button) findViewById(R.id.button);
        large=(Button) findViewById(R.id.button3) ;
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go;
                go = new Intent(menu.this,Gamescreen.class);
                go.putExtra("size","5");
                startActivity(go);
            }
        });
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go;
                go = new Intent(menu.this,Gamescreen.class);
                go.putExtra("size","4");
                startActivity(go);
            }
        });
        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go;
                go = new Intent(menu.this,Gamescreen.class);
                go.putExtra("size","6");
                startActivity(go);
            }
        });
    }
}
