package com.michelenadevelopment.calculadorapaypal;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class Option1Activity extends AppCompatActivity {

    private AdView mAdViewOption1;

    private EditText montoAEnviarOption1, comisionOption1, montoARecibirOption1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);


        //Inicializacion de las Ads
        MobileAds.initialize(this, "ca-app-pub-7656486697746182~8725726230");

        // Load an Ad
        mAdViewOption1 = findViewById(R.id.adViewOption1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewOption1.loadAd(adRequest);

        // Declaracion de Botones
        Button buttonCalculate, buttonClear;

        montoAEnviarOption1 = findViewById(R.id.editText_Option1_MontoAEnviar);
        comisionOption1 = findViewById(R.id.editText_Option1_Comision);
        montoARecibirOption1 = findViewById(R.id.editText_Option1_MontoARecibir);
        buttonCalculate = findViewById(R.id.button_Option1_Calculate);
        buttonClear = findViewById(R.id.button_Option1_Clear);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                Double montoAEnviarDouble;

                try{
                    montoAEnviarDouble = Double.valueOf(montoAEnviarOption1.getText().toString());
                } catch (NumberFormatException e){
                    e.fillInStackTrace();
                    montoAEnviarDouble = 0.0;
                }

                calculateOption1(montoAEnviarDouble);

            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montoAEnviarOption1.setText("");
                comisionOption1.setText("");
                montoARecibirOption1.setText("");
            }
        });

    }

    /**
     * Calcula el monto a recibir y la comision
     * @param montoAEnviar: monto que se enviara
     */
    @SuppressLint("DefaultLocale")
    public void calculateOption1(Double montoAEnviar){

        if (montoAEnviar > 0){
            double comisionDouble = (montoAEnviar*0.054) + 0.3;
            double montoARecibirDouble = montoAEnviar - comisionDouble;

            montoAEnviarOption1.setText(String.format("$%.2f",montoAEnviar));
            comisionOption1.setText(String.format("$%.2f",comisionDouble));
            montoARecibirOption1.setText(String.format("$%.2f",montoARecibirDouble));
        }
    }
}
