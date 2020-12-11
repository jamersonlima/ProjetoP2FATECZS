package com.example.carperformance.controller;

import android.content.Context;
import android.database.Cursor;

import com.example.carperformance.bean.Performance;
import com.example.carperformance.dao.DaoPerformance;

public class ControlePerformance {
    private DaoPerformance dao;
    private Context context;

    public ControlePerformance(Context context) throws Exception, ClassNotFoundException {
        this.context = context;
        this.dao = new DaoPerformance(this.context);
    }

    public String inserir(Performance per) throws ClassNotFoundException, Exception {

        return this.dao.inserir(per);
    }

    public String alterar(Performance per) throws ClassNotFoundException, Exception {
        return this.dao.alterar(per);
    }

    public Cursor buscar(Performance per) throws ClassNotFoundException, Exception {
        return this.dao.buscar(per);
    }

    public Cursor listar() throws ClassNotFoundException, Exception {
        return this.dao.listar();
    }

    public String remover(Performance per) throws ClassNotFoundException, Exception {
        return this.dao.remover(per);
    }
}