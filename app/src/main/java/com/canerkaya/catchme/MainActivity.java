package com.canerkaya.catchme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    TextView maxScorer;
    int score;
    int maxScore;
    SharedPreferences sharedPreferences;

    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        maxScorer=findViewById(R.id.maxScorer);
        imageArray=new ImageView[] {imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10};
        hideImages();
        sharedPreferences=this.getSharedPreferences("com.canerkaya.catchme", Context.MODE_PRIVATE);
        maxScore=sharedPreferences.getInt("maxScore",0);
        score=0;
        if (maxScore!=0){
            maxScor(0,maxScore);
        }


        new CountDownTimer(16000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Süre : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("ZAMAN BİTTİ");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar oynamak ister misin?");
                alert.setMessage("");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,  "Oyun bitti",Toast.LENGTH_SHORT).show();
                        Intent intent2= new Intent(getApplicationContext(),Main2Activity.class);
                        finish();
                        startActivity(intent2);

                    }
                });
                alert.show();
            }
        }.start();
    }
    public void skorPlus(View view){
        score++;
        scoreText.setText("Puan : "+score);
        maxScor(score,maxScore);
    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                int i= (int) (Math.random()*9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);
            }
        };
        handler.post(runnable);

    }
    public void maxScor(int score,int maxScore){
        if (score > maxScore){
            maxScore=score;
            sharedPreferences.edit().putInt("maxScore",maxScore).apply();
        }
        maxScorer.setText("En yüksek Puan : "+maxScore);
    }
}
