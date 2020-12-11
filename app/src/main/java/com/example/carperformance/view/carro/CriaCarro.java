package com.example.carperformance.view.carro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carperformance.R;
import com.example.carperformance.bean.Carro;
import com.example.carperformance.controller.ControleCarro;
import com.example.carperformance.view.Menu;

public class CriaCarro extends AppCompatActivity {

    EditText txtMarca, txtModelo, txtVelocidade;

    Carro bean;
    ControleCarro controller;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_carro);

        txtMarca = (EditText) findViewById(R.id.modeloTxt);
        txtModelo = (EditText) findViewById(R.id.marcaTxt);
        txtVelocidade = (EditText) findViewById(R.id.velocidadeTxt);

    }

    public void cadastrarCarro(View v) {
        try {
            bean = new Carro();
            bean.setMarca(txtMarca.getText().toString());
            bean.setModelo(txtModelo.getText().toString());
            bean.setVelocidade(Double.parseDouble(txtVelocidade.getText().toString()));


            controller = new ControleCarro(this);
            String resultado = controller.inserir(bean);


            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            intent = new Intent(CriaCarro.this, Menu.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarCadastroCarro(View v) {
        super.onBackPressed();
        finish();
    }
}