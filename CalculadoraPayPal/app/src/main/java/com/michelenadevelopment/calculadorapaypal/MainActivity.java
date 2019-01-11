package com.michelenadevelopment.calculadorapaypal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private AdView mAdViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializacion de las Ads
        MobileAds.initialize(this, getText(R.string.admob_app_id).toString());

        // Load an Ad
        mAdViewMain = findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewMain.loadAd(adRequest);

        Button buttonCalculoRapido = findViewById(R.id.buttonCalculoRapido);
        Button buttonCalculoAvanzado = findViewById(R.id.buttonCalculoAvanzado);
        Button buttonConfiguraciones = findViewById(R.id.buttonConfiguraciones);

        buttonCalculoRapido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CalculoRapidoActivity.class);
                startActivity(i);
            }
        });

        buttonCalculoAvanzado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelectCountryActivity.class);
                startActivity(i);
            }
        });

        buttonConfiguraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ConfiguracionActivity.class);
                startActivity(i);

            }
        });

    }

}
