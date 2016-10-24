package com.dasfinalapp.ong;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dasfinalapp.ong.controllers.ListONGActivity;

public class Splash_Screen_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash__screen_);

        // Just set the thread
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(Splash_Screen_Activity.this, ListONGActivity.class);
                    startActivity(intent);
                }catch(InterruptedException e){
                    e.printStackTrace();
                    Intent intent = new Intent(Splash_Screen_Activity.this, ListONGActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}
