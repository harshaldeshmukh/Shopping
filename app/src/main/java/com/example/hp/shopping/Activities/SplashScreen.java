package com.example.hp.shopping.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.shopping.Activities.CellPhone;
import com.example.hp.shopping.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                    Intent intent = new Intent(getApplicationContext(), CellPhone.class);
                    startActivity(intent);
                    finish();

            }

        }, 2500L);
    }
}
