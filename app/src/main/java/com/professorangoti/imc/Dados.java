package com.professorangoti.imc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dados {

    private double peso;
    private double altura;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}
