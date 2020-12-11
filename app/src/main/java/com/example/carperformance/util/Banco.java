package com.example.carperformance.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {

    public Banco (Context context) {
        super(context, "banco.db", null, 4);

    }

    @Override
    public void onOpen(SQLiteDatabase db){

        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("SQLITE", "CREATED!");

        db.execSQL("create table if not exists usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT,login VARCHAR(255) not null unique ,senha VARCHAR(255), cpf VARCHAR(255), nome VARCHAR(255), sobrenome VARCHAR(255));");

        db.execSQL("create table if not exists carros (_id INTEGER PRIMARY KEY AUTOINCREMENT,marca VARCHAR(255) not null unique ,modelo VARCHAR(255),velocidade DOUBLE);");

        db.execSQL("create table if not exists performances ( _id INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, id_carro INTEGER, motor varchar(255), tipo VARCHAR(255), preco DOUBLE, ano DOUBLE, aceleracao VARCHAR(255),foreign key (id_usuario) references usuarios (_id), foreign key (id_carro) references carros (_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("SQLITE", "UPGRADED!");
        db.execSQL("DROP TABLE IF EXISTS `carros`");
        db.execSQL("DROP TABLE IF EXISTS `performances`");
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        onCreate(db);

    }
}

