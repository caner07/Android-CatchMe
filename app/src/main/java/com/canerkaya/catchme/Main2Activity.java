package com.canerkaya.catchme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView);
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                if (textView.getVisibility()==textView.VISIBLE){
                    textView.setVisibility(View.INVISIBLE);
                }else {
                    textView.setVisibility(View.VISIBLE);
                }
                handler.postDelayed(runnable,180);
            }
        };
        handler.post(runnable);
    }
    public void start(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
