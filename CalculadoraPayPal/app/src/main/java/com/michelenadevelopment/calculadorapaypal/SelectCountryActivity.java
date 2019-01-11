package com.michelenadevelopment.calculadorapaypal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class SelectCountryActivity extends AppCompatActivity {

    private AdView mAdViewSelectCountry;
    private Spinner spinnerSeleccionPaisEnvia, spinnerSeleccionPaisRecibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        // Inicializacion de las Ads
        MobileAds.initialize(this, getText(R.string.admob_app_id).toString());

        // Load an Ad
        mAdViewSelectCountry = findViewById(R.id.adViewSelectCountry);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewSelectCountry.loadAd(adRequest);

        spinnerSeleccionPaisEnvia = findViewById(R.id.spinner_seleccion_pais_envia);
        spinnerSeleccionPaisRecibe = findViewById(R.id.spinner_seleccion_pais_recibe);
        Button buttonSeleccionPais = findViewById(R.id.button_seleccion_pais);

        // Lista de paises
        String country[]= new String[]{
                getString(R.string.country_usa),
                getString(R.string.country_otrosPaises)};

        // Set Spinner de paises disponibles
        ArrayAdapter <String> arrayPaises = new ArrayAdapter<>
                (this,R.layout.custom_spinner, country);

        spinnerSeleccionPaisEnvia.setAdapter(arrayPaises);
        spinnerSeleccionPaisRecibe.setAdapter(arrayPaises);

        // Button Siguiente
        buttonSeleccionPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectCountryActivity.this, SelectTransactionActivity.class);
                i.putExtra("seleccionPaisEnvia", spinnerSeleccionPaisEnvia.getSelectedItem().toString());
                i.putExtra("seleccionPaisRecibe", spinnerSeleccionPaisRecibe.getSelectedItem().toString());
                startActivity(i);
            }
        });

    }
}
