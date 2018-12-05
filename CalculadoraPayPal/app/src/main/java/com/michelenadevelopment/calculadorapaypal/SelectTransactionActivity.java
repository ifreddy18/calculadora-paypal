package com.michelenadevelopment.calculadorapaypal;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;

import static com.michelenadevelopment.calculadorapaypal.R.string.country_usa;

public class SelectTransactionActivity extends AppCompatActivity {

    private AdView mAdViewSelectTransaction;
    private Spinner spinnerSeleccionTransaccion;
    //private CheckBox cbSeleccionTransaccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transaction);

        // Inicializacion de las Ads
        MobileAds.initialize(this, "ca-app-pub-7656486697746182~8725726230");

        // Load an Ad
        mAdViewSelectTransaction = findViewById(R.id.adViewSelectTransaction);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewSelectTransaction.loadAd(adRequest);

        spinnerSeleccionTransaccion = findViewById(R.id.spinner_seleccion_transaccion);
        //cbSeleccionTransaccion= findViewById(R.id.cb_seleccion_transaccion);
        Button buttonSeleccionTransaccion = findViewById(R.id.button_seleccion_transaccion);

        // Lista Transacciones Estados Unidos (USD) a Estados Unidos (USD)
        String usaToUsa[] = new String[]{
                getString(R.string.transaction_usa_domesticaBalance),
                getString(R.string.transaction_usa_domesticaTarjeta),
                getString(R.string.transaction_usa_ventaInterior),
                getString(R.string.transaction_usa_hereTarjeta),
                getString(R.string.transaction_usa_hereManual),
                getString(R.string.transaction_usa_caridades)};
        // Lista Transacciones Estados Unidos (USD) a Otros Paises (USD)
        String usaToOtros[] = new String[]{
                getString(R.string.transaction_usa_internacionalEuropaBalance),
                getString(R.string.transaction_usa_internacionalEuropaTarjeta),
                getString(R.string.transaction_usa_internacionalOtrosBalance),
                getString(R.string.transaction_usa_internacionalOtrosTarjeta)};
        // Lista Transacciones Otros Paises (USD) a Estados Unidos (USD)
        String otrosToUsa[] = new String[]{
                getString(R.string.transaction_usa_ventaExterior),
                getString(R.string.transaction_usa_hereTarjeta),
                getString(R.string.transaction_usa_hereManual),
                getString(R.string.transaction_usa_caridades)};
        // Lista Transacciones Otros Paises (USD) a Otros Paises (USD)
        String otrosToOtros[] = new String[]{
                getString(R.string.transaction_otros_domesticoInternacional)};

        // Obtencion de la seleccion de pais
        final String seleccionPaisEnvia = getIntent().getStringExtra("seleccionPaisEnvia");
        final String seleccionPaisRecibe = getIntent().getStringExtra("seleccionPaisRecibe");

        // Condicion para set Spinner y divisa segun pais seleccionado en SelectCountryActivity
        ArrayAdapter <String> arrayTransaccion;
        final String divisa;
        if (seleccionPaisEnvia.equals(getString(R.string.country_usa))){
            divisa = getString(R.string.divisa_USD);
            if (seleccionPaisRecibe.equals(getString(R.string.country_usa))){
                arrayTransaccion = new ArrayAdapter<String>(this,R.layout.custom_spinner, usaToUsa);
            } else if (seleccionPaisRecibe.equals(getString(R.string.country_otrosPaises))){
                arrayTransaccion = new ArrayAdapter<String>(this,R.layout.custom_spinner, usaToOtros);
            } else {
                arrayTransaccion = null;
                Toast.makeText(this,"No hay pais seleccionado",Toast.LENGTH_LONG).show();
            }
        } else if (seleccionPaisEnvia.equals(getString(R.string.country_otrosPaises))){
            divisa = getString(R.string.divisa_USD);
            if (seleccionPaisRecibe.equals(getString(R.string.country_usa))){
                arrayTransaccion = new ArrayAdapter<String>(this,R.layout.custom_spinner, otrosToUsa);
            } else if (seleccionPaisRecibe.equals(getString(R.string.country_otrosPaises))){
                arrayTransaccion = new ArrayAdapter<String>(this,R.layout.custom_spinner, otrosToOtros);
            } else {
                arrayTransaccion = null;
                Toast.makeText(this,"No hay pais seleccionado",Toast.LENGTH_LONG).show();
            }
        } else {
            arrayTransaccion = null;
            divisa = "";
            Toast.makeText(this,"No hay pais seleccionado",Toast.LENGTH_LONG).show();
        }

        spinnerSeleccionTransaccion.setAdapter(arrayTransaccion);


        // Button Siguiente
        buttonSeleccionTransaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                String option = getIntent().getStringExtra("option");
                if (option.equals("Option1")){
                    i = new Intent(SelectTransactionActivity.this, Option1Activity.class);
                } else {
                    i = new Intent(SelectTransactionActivity.this, Option2Activity.class);
                }

                i.putExtra("seleccionPaisEnvia", seleccionPaisEnvia);
                i.putExtra("seleccionPaisRecibe", seleccionPaisRecibe);
                i.putExtra("seleccionTransaccion", spinnerSeleccionTransaccion.getSelectedItem().toString());
                i.putExtra("divisa", divisa);

                startActivity(i);
            }
        });

    }

    
}
