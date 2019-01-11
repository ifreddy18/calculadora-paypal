package com.michelenadevelopment.calculadorapaypal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // Inicializacion de las Ads
        MobileAds.initialize(this, "ca-app-pub-7656486697746182~8725726230");

        // Timer que indica la duracion del Launcher en pantalla
        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        },3000);
    }
}
