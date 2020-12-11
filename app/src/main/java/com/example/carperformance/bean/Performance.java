package com.example.carperformance.bean;

public class Performance {

    private int id;
    private int idU;
    private int idC;
    private String motor;
    private String tipo;
    private double preco;
    private double ano;
    private String aceleracao;


    public Performance() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getAno() {
        return ano;
    }

    public void setAno(double ano) {
        this.ano = ano;
    }

    public String getAceleracao() {
        return aceleracao;
    }

    public void setAceleracao(String aceleracao) {
        this.aceleracao = aceleracao;
    }

}