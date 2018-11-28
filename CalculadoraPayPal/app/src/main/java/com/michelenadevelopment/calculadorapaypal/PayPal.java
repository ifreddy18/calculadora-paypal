package com.michelenadevelopment.calculadorapaypal;

public class PayPal {

    private double enviado, recibido, comisionPorcentaje, comisionTasaFija;
    private String divisa;

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

    // Metodos para calcular monto a recibir

    protected void calcularMontoRecibir(){
        if (this.enviado > 0) {
            this.recibido = this.enviado - getComisionTotal();
        }
    }

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

}
