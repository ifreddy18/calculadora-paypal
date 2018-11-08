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

public class Option2Activity extends AppCompatActivity {

    private AdView mAdViewOption2;

    private EditText montoAEnviarOption2, montoARecibirOption2, comisionOption2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);

        //Inicializacion de las Ads
        MobileAds.initialize(this, "ca-app-pub-7656486697746182~8725726230");

        // Load an Ad
        mAdViewOption2 = findViewById(R.id.adViewOption2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewOption2.loadAd(adRequest);

        // Declaracion de Botones
        Button buttonCalculate, buttonClear;

        montoARecibirOption2 = findViewById(R.id.editText_Option2_MontoARecibir);
        comisionOption2 = findViewById(R.id.editText_Option2_Comision);
        montoAEnviarOption2 = findViewById(R.id.editText_Option2_MontoAEnviar);
        buttonCalculate = findViewById(R.id.button_Option2_Calculate);
        buttonClear = findViewById(R.id.button_Option2_Clear);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double montoARecibirDouble;

                try{
                    montoARecibirDouble = Double.valueOf(montoARecibirOption2.getText().toString());
                } catch (NumberFormatException e){
                    e.fillInStackTrace();
                    montoARecibirDouble = 0.0;
                }

                calculateOption2(montoARecibirDouble);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montoAEnviarOption2.setText("");
                comisionOption2.setText("");
                montoARecibirOption2.setText("");
            }
        });

    }

    /**
     * Calcula el monto a enviar y la comision
     * @param montoARecibir: monto a recibir
     */
    @SuppressLint("DefaultLocale")
    public void calculateOption2(Double montoARecibir){

        if (montoARecibir > 0){
            Double montoAEnviarDouble = (montoARecibir + 0.3)/0.946;
            Double comisionDouble = montoAEnviarDouble - montoARecibir;

            montoAEnviarOption2.setText(String.format("$%.2f",montoAEnviarDouble));
            comisionOption2.setText(String.format("$%.2f",comisionDouble));
            montoARecibirOption2.setText(String.format("$%.2f",montoARecibir));

        }
    }
}
