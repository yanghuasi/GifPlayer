package com.lihongxin.animationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.lihongxin.animationdemo.ByFrame.AnimationActivity;
import com.lihongxin.animationdemo.ByGlide.AnimationActivity4;
import com.lihongxin.animationdemo.ByVideoView.AnimationActivity3;
import com.lihongxin.animationdemo.ByWebview.AnimationActivity2;


public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
//    private AnimateOfRedPacketsView animateOfRedPacketsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AnimationActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AnimationActivity2.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AnimationActivity3.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AnimationActivity4.class));
            }
        });

    }
}
