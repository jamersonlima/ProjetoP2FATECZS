package com.example.carperformance.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carperformance.R;
import com.example.carperformance.view.carro.CriaCarro;
import com.example.carperformance.view.carro.ListaCarro;
import com.example.carperformance.view.performance.CriaPerformance;
import com.example.carperformance.view.performance.ListaPerformance;
import com.example.carperformance.view.usuario.ListaUsuario;

public class Menu extends AppCompatActivity {

    Intent tela;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //this.setTitle("Menu de acesso");
       // intent = getIntent();
    }

    public void listarUsuarios(View v) {
        tela = new Intent(Menu.this, ListaUsuario.class);
        this.startActivity(tela);
    }

    public void listarCarros(View v) {
        tela = new Intent(Menu.this, ListaCarro.class);
        this.startActivity(tela);
    }

    public void novaCarro(View v) {
        tela = new Intent(Menu.this, CriaCarro.class);
        this.startActivity(tela);
    }

    public void listarPerformances(View v) {
        tela = new Intent(Menu.this, ListaPerformance.class);
        this.startActivity(tela);
    }

    public void novaPerformances(View v) {
        tela = new Intent(Menu.this, CriaPerformance.class);
        this.startActivity(tela);
    }

    public void logout(View v) {
        tela = new Intent(Menu.this, Login.class);
        this.startActivity(tela);
    }
}