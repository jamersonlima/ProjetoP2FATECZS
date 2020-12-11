package com.example.carperformance.controller;

import android.content.Context;
import android.database.Cursor;


import com.example.carperformance.bean.Carro;
import com.example.carperformance.dao.DaoCarro;

public class ControleCarro {

    private DaoCarro dao;
    private Context context;

    public ControleCarro(Context context) throws Exception, ClassNotFoundException {
        this.context = context;
        this.dao = new DaoCarro(this.context);
    }

    public String inserir(Carro carro) throws ClassNotFoundException, Exception {

        return this.dao.inserir(carro);
    }

    public String alterar(Carro carro) throws ClassNotFoundException, Exception {
        return this.dao.alterar(carro);
    }

    public Cursor buscar(Carro carro) throws ClassNotFoundException, Exception {
        return this.dao.buscar(carro);
    }

    public Cursor listar(Carro carro) throws ClassNotFoundException, Exception {
        return this.dao.listar(carro);
    }

    public String remover(Carro carro) throws ClassNotFoundException, Exception {
        return this.dao.remover(carro);
    }
}