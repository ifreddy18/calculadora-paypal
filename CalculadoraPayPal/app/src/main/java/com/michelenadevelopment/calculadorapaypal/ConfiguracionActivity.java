package com.michelenadevelopment.calculadorapaypal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ConfiguracionActivity extends AppCompatActivity {

    private AdView mAdViewConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        // Inicializacion de las Ads
        MobileAds.initialize(this, getText(R.string.admob_app_id).toString());

        // Load an Ad
        mAdViewConfiguracion = findViewById(R.id.adViewConfiguracion);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewConfiguracion.loadAd(adRequest);

        Button buttonAgregarComision = findViewById(R.id.buttonAgregarComision);

        buttonAgregarComision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfiguracionActivity.this, R.string.message_option, Toast.LENGTH_LONG).show();
            }
        });
    }
}
