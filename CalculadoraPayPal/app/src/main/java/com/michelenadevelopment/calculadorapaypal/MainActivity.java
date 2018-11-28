package com.michelenadevelopment.calculadorapaypal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializacion de las Ads
        MobileAds.initialize(this, "ca-app-pub-7656486697746182~8725726230");

        // Load an Ad
        mAdViewMain = findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewMain.loadAd(adRequest);

        Button buttonOption1 = findViewById(R.id.buttonOption1);
        Button buttonOption2 = findViewById(R.id.buttonOption2);

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelectCountryActivity.class);
                i.putExtra("option","Option1");
                startActivity(i);

            }
        });

        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelectCountryActivity.class);
                i.putExtra("option","Option2");
                startActivity(i);
            }
        });

    }

}
