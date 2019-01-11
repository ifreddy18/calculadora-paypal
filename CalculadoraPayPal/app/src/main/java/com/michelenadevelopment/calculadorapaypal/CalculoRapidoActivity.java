package com.michelenadevelopment.calculadorapaypal;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class CalculoRapidoActivity extends AppCompatActivity {

    private AdView mAdViewCalculoRapido;
    private InterstitialAd mInterstitialAdCalculoRapido;
    private static int countInterstitial = 0;
    private static PayPal paypal = new PayPal();
    private TextView datoEntrada, datoComision, datoSalida,
            tagEntrada, tagSalida;
    private Switch aSwitch;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_rapido);

        // Inicializacion de las Ads Banner
        MobileAds.initialize(this, getText(R.string.admob_app_id).toString());
        // Load an Ad Banner
        mAdViewCalculoRapido = findViewById(R.id.adViewCalculoRapido);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewCalculoRapido.loadAd(adRequest);

        // Inicializacion Ads Interstitial
        mInterstitialAdCalculoRapido = new InterstitialAd(this);
        mInterstitialAdCalculoRapido.setAdUnitId(getText(R.string.admob_intersticialCalculoRapido).toString());// Anuncio Real
        //mInterstitialAdCalculoRapido.setAdUnitId(getText(R.string.admob_adPrueba_instertitial).toString());// Anuncio de prueba
        // Load Ads Interstitial
        mInterstitialAdCalculoRapido.loadAd(new AdRequest.Builder().build());

        // Declaracion de Variables Locales
        Button buttonEquals, buttonClear, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5,
                btn_6, btn_7, btn_8, btn_9, btn_Dot;


        // Asignacion de los botones
        buttonEquals = findViewById(R.id.btnNumber_Equals);
        buttonClear = findViewById(R.id.btnNumber_Clear);
        btn_0 = findViewById(R.id.btnNumber_0);
        btn_1 = findViewById(R.id.btnNumber_1);
        btn_2 = findViewById(R.id.btnNumber_2);
        btn_3 = findViewById(R.id.btnNumber_3);
        btn_4 = findViewById(R.id.btnNumber_4);
        btn_5 = findViewById(R.id.btnNumber_5);
        btn_6 = findViewById(R.id.btnNumber_6);
        btn_7 = findViewById(R.id.btnNumber_7);
        btn_8 = findViewById(R.id.btnNumber_8);
        btn_9 = findViewById(R.id.btnNumber_9);
        btn_Dot = findViewById(R.id.btnNumber_Dot);

        aSwitch = findViewById(R.id.aSwitch);
        spinner = findViewById(R.id.spinner);
        tagEntrada = findViewById(R.id.tvEntrada_tag);
        tagSalida = findViewById(R.id.tvSalida_tag);
        datoEntrada = findViewById(R.id.tvEntrada);
        datoComision = findViewById(R.id.tvComision);
        datoSalida = findViewById(R.id.tvSalida);


        // Spinner
        String comisionesFrecuentes[] = new String[]{
                getString(R.string.comision_otros_domesticoInternacional_1),
                getString(R.string.comision_usa_ventaExterior_1),
                getString(R.string.comision_usa_ventaInterior_1)};

        ArrayAdapter<String> comisionesFrecuentesAdapter = new ArrayAdapter<String>(
                this,R.layout.custom_spinner, comisionesFrecuentes);

        spinner.setAdapter(comisionesFrecuentesAdapter);

        // Listeners para los botones

        View.OnClickListener listenerNumbers = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String monto = datoEntrada.getText().toString();
                monto = monto + button.getText().toString();
                datoEntrada.setText(monto);
            }
        };

        View.OnClickListener listenerEquals = new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                // Set de las comisiones aplicadas en los atributos de la clase PayPal
                setComisionesPayPal(spinner.getSelectedItem().toString());

                if (aSwitch.isChecked()){ // Calculo Invertido
                    try {
                        paypal.setRecibido(Double.valueOf(datoEntrada.getText().toString()));
                        paypal.calcularMontoEnviar();
                        datoEntrada.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getRecibido()));
                        datoComision.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getComisionTotal()));
                        datoSalida.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getEnviado()));
                    } catch (Exception e){
                        e.fillInStackTrace();
                    }
                } else { // Calculo normal
                    try {
                        paypal.setEnviado(Double.valueOf(datoEntrada.getText().toString()));
                        paypal.calcularMontoRecibir();
                        datoEntrada.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getEnviado()));
                        datoComision.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getComisionTotal()));
                        datoSalida.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getRecibido()));
                    } catch (Exception e){
                        e.fillInStackTrace();
                    }
                }
            }
        };

        View.OnClickListener listenerClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countInterstitial++;
                if (mInterstitialAdCalculoRapido.isLoaded() && countInterstitial==5){
                    mInterstitialAdCalculoRapido.show();
                    countInterstitial = 0;
                }

                datoEntrada.setText("");
                datoComision.setText("");
                datoSalida.setText("");
            }
        };

        btn_0.setOnClickListener(listenerNumbers);
        btn_1.setOnClickListener(listenerNumbers);
        btn_2.setOnClickListener(listenerNumbers);
        btn_3.setOnClickListener(listenerNumbers);
        btn_4.setOnClickListener(listenerNumbers);
        btn_5.setOnClickListener(listenerNumbers);
        btn_6.setOnClickListener(listenerNumbers);
        btn_7.setOnClickListener(listenerNumbers);
        btn_8.setOnClickListener(listenerNumbers);
        btn_9.setOnClickListener(listenerNumbers);
        btn_Dot.setOnClickListener(listenerNumbers);

        buttonEquals.setOnClickListener(listenerEquals);
        buttonClear.setOnClickListener(listenerClear);

        // Eventos de la Ad Intersticial
        mInterstitialAdCalculoRapido.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mInterstitialAdCalculoRapido.loadAd(new AdRequest.Builder().build());
            }
        });


    }

    public void onClickSwitch(View view) {
        if (aSwitch.isChecked()){
            tagEntrada.setText(R.string.ingrese_monto_a_recibir);
            tagSalida.setText(R.string.monto_a_enviar);
        } else {
            tagEntrada.setText(R.string.ingrese_monto_a_enviar);
            tagSalida.setText(R.string.monto_a_recibir);
        }

        datoEntrada.setText("");
        datoComision.setText("");
        datoSalida.setText("");

    }

    /**
     * Metodo que hace set a los valores de la comision de tipo double, segun el string suministrado
     * @param comisionesString
     */
    public void setComisionesPayPal(String comisionesString) {
        if (comisionesString.equals(getText(R.string.comision_usa_domesticaBalance_1).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_domesticaTarjeta_1).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.30);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaBalance_1).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaBalance_2).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaBalance_3).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaTarjeta_1).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaTarjeta_2).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalEuropaTarjeta_3).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosBalance_1).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosBalance_2).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosBalance_3).toString())) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(4.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosTarjeta_1).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosTarjeta_2).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_internacionalOtrosTarjeta_3).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(4.99);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_ventaInterior_1).toString())) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.30);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_ventaExterior_1).toString())) {
            paypal.setComisionPorcentaje(4.4);
            paypal.setComisionTasaFija(0.30);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_hereTarjeta_1).toString())) {
            paypal.setComisionPorcentaje(2.7);
            paypal.setComisionTasaFija(0);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_hereManual_1).toString())) {
            paypal.setComisionPorcentaje(3.5);
            paypal.setComisionTasaFija(0);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_usa_caridades_1).toString())) {
            paypal.setComisionPorcentaje(2.2);
            paypal.setComisionTasaFija(0.30);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else if (comisionesString.equals(getText(R.string.comision_otros_domesticoInternacional_1).toString())) {
            paypal.setComisionPorcentaje(5.4);
            paypal.setComisionTasaFija(0.30);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        } else {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0);
            paypal.setDivisa(getText(R.string.divisa_USD).toString());
        }
    }
}
