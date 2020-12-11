package com.example.carperformance.view.carro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.carperformance.bean.Carro;
import com.example.carperformance.controller.ControleCarro;
import com.example.carperformance.R;

import com.example.carperformance.view.Menu;

public class EditaCarro extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    EditText editaMarca, editaModelo, editaVelocidade;

    Carro bean;
    ControleCarro controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_carro);

        bundle = getIntent().getExtras();

        editaMarca = (EditText) findViewById(R.id.marca2Txt2);
        editaModelo = (EditText) findViewById(R.id.modeloTxt2);
        editaVelocidade = (EditText) findViewById(R.id.velocidadeTxt2);


        editaMarca.setText(bundle.getString("marca"));
        editaModelo.setText(bundle.getString("modelo"));
        editaVelocidade.setText(String.valueOf(bundle.getDouble("velocidade")));

    }

    public void alterarCarro(View v) {
        try {
            bean = new Carro();
            bean.setId(bundle.getInt("_id"));
            bean.setMarca(editaMarca.getText().toString());
            bean.setModelo(editaModelo.getText().toString());
            bean.setVelocidade(Double.parseDouble(editaVelocidade.getText().toString()));


            controller = new ControleCarro(this);
            String resposta = controller.alterar(bean);
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            intent = new Intent(EditaCarro.this, Menu.class);
            this.startActivity(intent);
            finish();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarAlteracaoCarro(View v) {
        intent = new Intent(EditaCarro.this, Menu.class);
        this.startActivity(intent);
        finish();
    }
}