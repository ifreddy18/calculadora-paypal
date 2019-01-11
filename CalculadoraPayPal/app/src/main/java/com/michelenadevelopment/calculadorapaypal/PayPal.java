package com.michelenadevelopment.calculadorapaypal;

public class PayPal {

    private double enviado, recibido, comisionPorcentaje, comisionTasaFija;
    private String divisa;
    private String paisEnvia, paisRecibe, tipoTransaccion;

    // Constructor

    public PayPal(){

    }

    // Getter and Setter

    protected double getEnviado() {
        return enviado;
    }

    protected void setEnviado(double enviado) {
        this.enviado = enviado;
    }

    protected double getRecibido() {
        return recibido;
    }

    protected void setRecibido(double recibido) {
        this.recibido = recibido;
    }

    protected double getComisionPorcentaje() {
        return comisionPorcentaje;
    }

    protected void setComisionPorcentaje(double comisionPorcentaje) {
        this.comisionPorcentaje = comisionPorcentaje/100;
    }

    protected double getComisionTasaFija() {
        return comisionTasaFija;
    }

    protected void setComisionTasaFija(double comisionTasaFija) {
        this.comisionTasaFija = comisionTasaFija;

    }

    protected String getDivisa(){
        return divisa;
    }

    protected void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    protected String getPaisEnvia() {
        return paisEnvia;
    }

    protected void setPaisEnvia(String paisEnvia) {
        this.paisEnvia = paisEnvia;
    }

    protected String getPaisRecibe() {
        return paisRecibe;
    }

    protected void setPaisRecibe(String paisRecibe) {
        this.paisRecibe = paisRecibe;
    }

    protected String getTipoTransaccion() {
        return tipoTransaccion;
    }

    protected void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }


    // Metodos para calcular monto a recibir

    /**
     * Metodo void que le asigna un valor al atributo Recibido de la clase teniendo
     * ya el monto enviado
     */
    protected void calcularMontoRecibir(){
        if (this.enviado > 0) {
            this.recibido = this.enviado - getComisionTotal();
        }
    }

    /**
     * Metodo que devuelve el monto a Recibir como un Double
     * @param montoAEnviar: Monto enviado
     * @param comisionPorcentaje: Comision en porcentaje
     * @param comisionTasaFija: Comision en tasa fija
     * @return
     */
    protected double calcularMontoRecibir(double montoAEnviar, double comisionPorcentaje, double comisionTasaFija){
        double montoARecibir;
        if (montoAEnviar > 0){
            montoARecibir = (montoAEnviar*comisionPorcentaje) + comisionTasaFija;
        } else {
            montoARecibir = 0.0;
        }

        return montoARecibir;
    }

    // Metodos para calcular el monto a enviar

    protected void calcularMontoEnviar(){
        if (this.recibido > 0){
            this.enviado = (this.recibido + this.comisionTasaFija)/(1 - this.comisionPorcentaje);
        }
    }

    /**
     * Metodo para calcular la comision total de la variable
     * @return
     */
    protected double getComisionTotal(){
        return (this.enviado * this.comisionPorcentaje) + this.comisionTasaFija;
    }

    // Metodos para convertir String en Double


    /**
     * Metodo que devuelve el string de la comision que se aplica segun la el valor double
     * suministrado y el String del tipo de transaccion seleccionado
     * @param transaccion: Tipo de transaccion
     * @param montoAEnviar: Monto a enviar
     * @return String con la comision que aplica
     */
    public String seleccionarComision(String transaccion, Double montoAEnviar){
        String comisionesString;
        if (transaccion.equals(String.valueOf(R.string.transaction_usa_domesticaBalance))){
            comisionesString = String.valueOf(R.string.comision_usa_domesticaBalance_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_domesticaTarjeta))){
            comisionesString = String.valueOf(R.string.comision_usa_domesticaTarjeta_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_internacionalEuropaBalance))){
            if (montoAEnviar<50){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaBalance_1);
            } else if (montoAEnviar>=50.00 && montoAEnviar<100){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaBalance_2);
            } else {
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaBalance_3);
            }
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_internacionalEuropaTarjeta))){
            if (montoAEnviar<50){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaTarjeta_1);
            } else if (montoAEnviar>=50.00 && montoAEnviar<100){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaTarjeta_2);
            } else {
                comisionesString = String.valueOf(R.string.comision_usa_internacionalEuropaTarjeta_3);
            }
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_internacionalOtrosBalance))){
            if (montoAEnviar<50){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosBalance_1);
            } else if (montoAEnviar>=50.00 && montoAEnviar<100){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosBalance_2);
            } else {
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosBalance_3);
            }
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_internacionalOtrosTarjeta))){
            if (montoAEnviar<50){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosTarjeta_1);
            } else if (montoAEnviar>=50.00 && montoAEnviar<100){
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosTarjeta_2);
            } else {
                comisionesString = String.valueOf(R.string.comision_usa_internacionalOtrosTarjeta_3);
            }
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_ventaInterior))){
            comisionesString = String.valueOf(R.string.comision_usa_ventaInterior_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_ventaExterior))){
            comisionesString = String.valueOf(R.string.comision_usa_ventaExterior_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_hereTarjeta))){
            comisionesString = String.valueOf(R.string.comision_usa_hereTarjeta_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_hereManual))){
            comisionesString = String.valueOf(R.string.comision_usa_hereManual_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_usa_caridades))){
            comisionesString = String.valueOf(R.string.comision_usa_caridades_1);
        } else if (transaccion.equals(String.valueOf(R.string.transaction_otros_domesticoInternacional))){
            comisionesString = String.valueOf(R.string.comision_otros_domesticoInternacional_1);
        } else {
            comisionesString = "ERROR";
        }
        return comisionesString;
    }
}
