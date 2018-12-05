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

public class Option2Activity extends AppCompatActivity {

    private static PayPal paypal = new PayPal();
    private AdView mAdViewOption2;
    private EditText montoARecibir;
    private TextView tipoComision, comision, montoAEnviar;


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

        // Asignacion de las View
        tipoComision = findViewById(R.id.tv_Option2_TipoDeComision);
        montoARecibir = findViewById(R.id.et_Option2_MontoARecibir);
        comision = findViewById(R.id.tv_Option2_Comision);
        montoAEnviar = findViewById(R.id.tv_Option2_MontoAEnviar);
        buttonCalculate = findViewById(R.id.button_Option2_Calculate);
        buttonClear = findViewById(R.id.button_Option2_Clear);

        // Obtencion de la seleccion de pais y transaccion
        final String seleccionTransaccion = getIntent().getStringExtra("seleccionTransaccion");
        paypal.setDivisa(getIntent().getStringExtra("divisa"));

        // Boton Calcular
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                Double montoARecibirDouble = Double.valueOf(montoARecibir.getText().toString());
                String comisionAplicada = seleccionarComision(seleccionTransaccion, montoARecibirDouble);

                // Condicionales para set de comisiones
                setComisionesPayPal(comisionAplicada);

                // Verifica que montoAEnviar devuelva un double
                try {
                    paypal.setRecibido(montoARecibirDouble);
                } catch (NumberFormatException e) {
                    e.fillInStackTrace();
                    paypal.setRecibido(0.0);
                }

                paypal.calcularMontoEnviar();

                tipoComision.setText(comisionAplicada);
                montoARecibir.setText(String.format("%.2f", paypal.getRecibido()));
                comision.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getComisionTotal()));
                montoAEnviar.setText(String.format("%s %.2f", paypal.getDivisa(), paypal.getEnviado()));
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

    /**
     * Metodo que hace set a los valores de la comision de tipo double, segun el string suministrado
     * @param comisionesString
     */
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

    /**
     * Metodo que devuelve el string de la comision que se aplica segun la el valor double
     * suministrado y el String del tipo de transaccion seleccionado
     * @param transaccion: Tipo de transaccion
     * @param montoARecibir: Monto a enviar
     * @return String con la comision que aplica
     */
    public String seleccionarComision(String transaccion, Double montoARecibir){
        String comision;
        if (transaccion.equals(getString(R.string.transaction_usa_domesticaBalance))){
            comision = getString(R.string.comision_usa_domesticaBalance_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_domesticaTarjeta))){
            comision = getString(R.string.comision_usa_domesticaTarjeta_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_internacionalEuropaBalance))){
            if (montoARecibir<50){
                comision = getString(R.string.comision_usa_internacionalEuropaBalance_1);
            } else if (montoARecibir>=50.00 && montoARecibir<100){
                comision = getString(R.string.comision_usa_internacionalEuropaBalance_2);
            } else {
                comision = getString(R.string.comision_usa_internacionalEuropaBalance_3);
            }
        } else if (transaccion.equals(getString(R.string.transaction_usa_internacionalEuropaTarjeta))){
            if (montoARecibir<50){
                comision = getString(R.string.comision_usa_internacionalEuropaTarjeta_1);
            } else if (montoARecibir>=50.00 && montoARecibir<100){
                comision = getString(R.string.comision_usa_internacionalEuropaTarjeta_2);
            } else {
                comision = getString(R.string.comision_usa_internacionalEuropaTarjeta_3);
            }
        } else if (transaccion.equals(getString(R.string.transaction_usa_internacionalOtrosBalance))){
            if (montoARecibir<50){
                comision = getString(R.string.comision_usa_internacionalOtrosBalance_1);
            } else if (montoARecibir>=50.00 && montoARecibir<100){
                comision = getString(R.string.comision_usa_internacionalOtrosBalance_2);
            } else {
                comision = getString(R.string.comision_usa_internacionalOtrosBalance_3);
            }
        } else if (transaccion.equals(getString(R.string.transaction_usa_internacionalOtrosTarjeta))){
            if (montoARecibir<50){
                comision = getString(R.string.comision_usa_internacionalOtrosTarjeta_1);
            } else if (montoARecibir>=50.00 && montoARecibir<100){
                comision = getString(R.string.comision_usa_internacionalOtrosTarjeta_2);
            } else {
                comision = getString(R.string.comision_usa_internacionalOtrosTarjeta_3);
            }
        } else if (transaccion.equals(getString(R.string.transaction_usa_ventaInterior))){
            comision = getString(R.string.comision_usa_ventaInterior_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_ventaExterior))){
            comision = getString(R.string.comision_usa_ventaExterior_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_hereTarjeta))){
            comision = getString(R.string.comision_usa_hereTarjeta_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_hereManual))){
            comision = getString(R.string.comision_usa_hereManual_1);
        } else if (transaccion.equals(getString(R.string.transaction_usa_caridades))){
            comision = getString(R.string.comision_usa_caridades_1);
        } else if (transaccion.equals(getString(R.string.transaction_otros_domesticoInternacional))){
            comision = getString(R.string.comision_otros_domesticoInternacional_1);
        } else {
            comision = "ERROR";
        }
        return comision;
    }

}
