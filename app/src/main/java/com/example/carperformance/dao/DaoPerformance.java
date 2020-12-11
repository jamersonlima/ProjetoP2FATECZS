package com.example.carperformance.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carperformance.bean.Performance;
import com.example.carperformance.util.Banco;

import java.util.ArrayList;
import java.util.List;

public class DaoPerformance {
    private SQLiteDatabase db;
    private Banco banco;


    public DaoPerformance(Context context) {
        super();
        this.banco = new Banco(context);

        this.db = banco.getWritableDatabase();

    }

    public String inserir(Performance per) throws Exception {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("id_usuario", per.getIdU());
        valores.put("id_carro", per.getIdC());
        valores.put("motor", per.getMotor());
        valores.put("tipo", per.getTipo());
        valores.put("preco", per.getPreco());
        valores.put("ano", per.getAno());
        valores.put("aceleracao", per.getAceleracao());


        resultado = db.insert("performances", null, valores);
        db.close();

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public String alterar(Performance per) throws Exception {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + per.getId();

        valores = new ContentValues();
        valores.put("id_usuario", per.getIdU());
        valores.put("id_carro", per.getIdC());
        valores.put("motor", per.getMotor());
        valores.put("tipo", per.getTipo());
        valores.put("preco", per.getPreco());
        valores.put("ano", per.getAno());
        valores.put("aceleracao", per.getAceleracao());


        long resultado = db.update("performances", valores, where, null);
        db.close();

        if (resultado == -1) return "Erro ao alterar registro";
        else return "Registro alterado com sucesso";
    }

    public String remover(Performance per) {
        String where = "_id = " + per.getId();

        db = banco.getReadableDatabase();
        long resultado = db.delete("performances", where, null);
        db.close();

        if (resultado == -1) return "Erro ao remover registro";
        else return "Registro removido com sucesso";
    }

    public Cursor listar() {
        Cursor cursor;
        String[] campos = {"_id", "id_usuario", "id_carro", "motor","tipo", "preco", "ano", "aceleracao"};
        db = banco.getReadableDatabase();
        cursor = db.query("performances", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscar(Performance per) {
        Cursor cursor;
        String[] campos = {"_id", "id_usuario", "id_carro", "motor","tipo", "ano", "preco", "aceleracao"};
        String where = "_id = " + per.getId();
        db = banco.getReadableDatabase();
        cursor = db.query("performances", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /*public String buscarTipo(Performance per) {
        Cursor cursor;
        String[] campos = {"marca"};
        String where = "_id = " + per.getIdC();

        cursor = db.query("carros", campos, where, null, null, null, null, null);
        String name = null;
        List<String> stringList = new ArrayList<String>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                name = (cursor.getString(cursor.getColumnIndex("marca")));

                stringList.add(name);
                cursor.moveToNext();
            }
        }

            return stringList.get(0);

    }*/
}
