package com.michelenadevelopment.calculadorapaypal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Option1Activity extends AppCompatActivity {

    private static PayPal paypal = new PayPal();
    private AdView mAdViewOption1;
    private EditText montoAEnviar;
    private TextView comision, montoARecibir;
    private Spinner spinnerComision;

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

        // Asignacion de las View
        montoAEnviar = findViewById(R.id.et_Option1_MontoAEnviar);
        comision = findViewById(R.id.tv_Option1_Comision);
        montoARecibir = findViewById(R.id.tv_Option1_MontoARecibir);
        buttonCalculate = findViewById(R.id.button_Option1_Calculate);
        buttonClear = findViewById(R.id.button_Option1_Clear);
        spinnerComision = findViewById(R.id.spinner_Option1);

        // Lista de Comisiones "Estados Unidos (USD)"
        String comisionUsaDomesticaBalance[] = new String[]{
                getString(R.string.comision_usa_domesticaBalance_1)};
        String comisionUsaDomesticaTarjeta[] = new String[]{
                getString(R.string.comision_usa_domesticaTarjeta_1)};
        String comisionUsaInternacionalEuropaBalance[] = new String[]{
                getString(R.string.comision_usa_internacionalEuropaBalance_1),
                getString(R.string.comision_usa_internacionalEuropaBalance_2),
                getString(R.string.comision_usa_internacionalEuropaBalance_3)};
        String comisionUsaInternacionalEuropaTarjeta[] = new String[]{
                getString(R.string.comision_usa_internacionalEuropaTarjeta_1),
                getString(R.string.comision_usa_internacionalEuropaTarjeta_2),
                getString(R.string.comision_usa_internacionalEuropaTarjeta_3)};
        String comisionUsaInternacionalOtrosBalance[] = new String[]{
                getString(R.string.comision_usa_internacionalOtrosBalance_1),
                getString(R.string.comision_usa_internacionalOtrosBalance_2),
                getString(R.string.comision_usa_internacionalOtrosBalance_3)};
        String comisionUsaInternacionalOtrosTarjeta[] = new String[]{
                getString(R.string.comision_usa_internacionalOtrosTarjeta_1),
                getString(R.string.comision_usa_internacionalOtrosTarjeta_2),
                getString(R.string.comision_usa_internacionalOtrosTarjeta_3)};
        String comisionUsaVentaInterior[] = new String[]{
                getString(R.string.comision_usa_ventaInterior_1)};
        String comisionUsaVentaExterior[] = new String[]{
                getString(R.string.comision_usa_ventaExterior_1)};
        String comisionUsaHereTarjeta[] = new String[]{
                getString(R.string.comision_usa_hereTarjeta_1)};
        String comisionUsaHereManual[] = new String[]{
                getString(R.string.comision_usa_hereManual_1)};
        String comisionUsaCaridades[] = new String[]{
                getString(R.string.comision_usa_caridades_1)};

        // Lista de Comisiones "Otros Paises (USD)"
        String comisionOtrosDomesticoInternacional[] = new String[]{
                getString(R.string.comision_otros_domesticoInternacional_1)};


        // Obtencion de la seleccion de pais y transaccion
        final String seleccionPais = getIntent().getStringExtra("seleccionPais");
        final String seleccionTransaccion = getIntent().getStringExtra("seleccionTransaccion");
        paypal.setDivisa(getIntent().getStringExtra("divisa"));

        // Condicion para set Spinner segun tipo de transaccion y pais seleccionado en SelectCountryActivity
        ArrayAdapter<String> arrayComision;

        if (seleccionPais.equals(getString(R.string.country_usa))) {
            if (seleccionTransaccion.equals(getString(R.string.transaction_usa_domesticaBalance))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaDomesticaBalance);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_domesticaTarjeta))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaDomesticaTarjeta);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_internacionalEuropaBalance))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaInternacionalEuropaBalance);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_internacionalEuropaTarjeta))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaInternacionalEuropaTarjeta);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_internacionalOtrosBalance))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaInternacionalOtrosBalance);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_internacionalOtrosTarjeta))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaInternacionalOtrosTarjeta);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_ventaInterior))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaVentaInterior);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_ventaExterior))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaVentaExterior);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_hereTarjeta))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaHereTarjeta);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_hereManual))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaHereManual);
            } else if (seleccionTransaccion.equals(getString(R.string.transaction_usa_caridades))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionUsaCaridades);
            } else {
                arrayComision = null;
            }
        } else if (seleccionPais.equals(getString(R.string.country_otrosPaises))) {
            if (seleccionTransaccion.equals(getString(R.string.transaction_otros_domesticoInternacional))) {
                arrayComision = new ArrayAdapter<>(this, R.layout.custom_spinner, comisionOtrosDomesticoInternacional);
            } else {
                arrayComision = null;
            }
        } else {
            arrayComision = null;
            Toast.makeText(this, "No hay pais seleccionado", Toast.LENGTH_LONG).show();
        }

        spinnerComision.setAdapter(arrayComision);


        // Boton Calcular
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String comisionString = spinnerComision.getSelectedItem().toString();

                // Condicionales para set de comisiones
                setComisionesPayPal(comisionString);

                // Verifica que montoAEnviar devuelva un double
                try {
                    paypal.setEnviado(Double.valueOf(montoAEnviar.getText().toString()));
                } catch (NumberFormatException e) {
                    e.fillInStackTrace();
                    paypal.setEnviado(0.0);
                }

                paypal.calcularMontoRecibir();

                montoAEnviar.setText(String.format("%.2f", paypal.getEnviado()));
                comision.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getComisionTotal()));
                montoARecibir.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getRecibido()));
            }
        });


        // Boton Clear
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montoAEnviar.setText("");
                comision.setText("");
                montoARecibir.setText("");
            }
        });

    }

    public void setComisionesPayPal(String comisionesString) {
        if (comisionesString.equals(getString(R.string.comision_usa_domesticaBalance_1))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0);
        } else if (comisionesString.equals(getString(R.string.comision_usa_domesticaTarjeta_1))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.30);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaBalance_1))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaBalance_2))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaBalance_3))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaTarjeta_1))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaTarjeta_2))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalEuropaTarjeta_3))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosBalance_1))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosBalance_2))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosBalance_3))) {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(4.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosTarjeta_1))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosTarjeta_2))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(2.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_internacionalOtrosTarjeta_3))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(4.99);
        } else if (comisionesString.equals(getString(R.string.comision_usa_ventaInterior_1))) {
            paypal.setComisionPorcentaje(2.9);
            paypal.setComisionTasaFija(0.30);
        } else if (comisionesString.equals(getString(R.string.comision_usa_ventaExterior_1))) {
            paypal.setComisionPorcentaje(4.4);
            paypal.setComisionTasaFija(0.30);
        } else if (comisionesString.equals(getString(R.string.comision_usa_hereTarjeta_1))) {
            paypal.setComisionPorcentaje(2.7);
            paypal.setComisionTasaFija(0);
        } else if (comisionesString.equals(getString(R.string.comision_usa_hereManual_1))) {
            paypal.setComisionPorcentaje(3.5);
            paypal.setComisionTasaFija(0);
        } else if (comisionesString.equals(getString(R.string.comision_usa_caridades_1))) {
            paypal.setComisionPorcentaje(2.2);
            paypal.setComisionTasaFija(0.30);
        } else if (comisionesString.equals(getString(R.string.comision_otros_domesticoInternacional_1))) {
            paypal.setComisionPorcentaje(5.4);
            paypal.setComisionTasaFija(0.30);
        } else {
            paypal.setComisionPorcentaje(0);
            paypal.setComisionTasaFija(0);
        }
    }
}
