package com.example.carperformance.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carperformance.bean.Carro;
import com.example.carperformance.util.Banco;

public class DaoCarro {

    private SQLiteDatabase db;
    private Banco banco;

    public DaoCarro(Context context) {
        super();
        this.banco = new Banco(context);
        this.db = banco.getWritableDatabase();

    }

    public String inserir(Carro carro) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("marca", carro.getMarca());
        valores.put("modelo", carro.getModelo());
        valores.put("velocidade", carro.getVelocidade());


        resultado = db.insert("carros", null, valores);
        db.close();

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public String alterar(Carro carro) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + carro.getId();

        valores = new ContentValues();
        valores.put("marca", carro.getMarca());
        valores.put("modelo", carro.getModelo());
        valores.put("velocidade", carro.getVelocidade());

        long resultado = db.update("carros", valores, where, null);
        db.close();

        if (resultado == -1) return "Erro ao alterar registro";
        else return "Registro alterado com sucesso";
    }

    public String remover(Carro carro) {
        String where = "_id = " + carro.getId();

        db = banco.getReadableDatabase();
        long resultado = db.delete("carros", where, null);
        db.close();

        if (resultado == -1) return "Erro ao remover registro";
        else return "Registro removido com sucesso";
    }

    public Cursor listar(Carro carro) {
        Cursor cursor;
        String[] campos = {"_id", "marca", "modelo", "velocidade"};
        db = banco.getReadableDatabase();
        cursor = db.query("carros", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscar(Carro carro) {
        Cursor cursor;
        String[] campos = {"_id", "marca", "modelo", "velocidade"};
        String where = "_id = " + carro.getId();
        db = banco.getReadableDatabase();
        cursor = db.query("carros", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}