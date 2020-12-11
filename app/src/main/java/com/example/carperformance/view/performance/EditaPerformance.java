package com.example.carperformance.view.performance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carperformance.R;
import com.example.carperformance.bean.Performance;
import com.example.carperformance.controller.ControlePerformance;
import com.example.carperformance.view.Menu;

public class EditaPerformance extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    EditText usuP, carP, motorP, tipoP, precoP, anoP, aceleracaoP;


    Performance bean;
    ControlePerformance controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_performance);

        bundle = getIntent().getExtras();


        usuP = (EditText) findViewById(R.id.usuTxt2);
        carP = (EditText) findViewById(R.id.carTxt2);
        motorP = (EditText) findViewById(R.id.motorTxt2);
        tipoP= (EditText) findViewById(R.id.tipoTxt2);
        precoP= (EditText) findViewById(R.id.precoTxt2);
        anoP= (EditText) findViewById(R.id.anoTxt2);
        aceleracaoP= (EditText) findViewById(R.id.aceleracaoTxt2);


        usuP.setText(String.valueOf(bundle.getInt("id_usuario")));
        carP.setText(String.valueOf(bundle.getInt("id_carro")));
        motorP.setText(bundle.getString("motor"));
        tipoP.setText(bundle.getString("tipo"));
        precoP.setText(String.valueOf(bundle.getDouble("preco")));
        anoP.setText(String.valueOf(bundle.getDouble("ano")));
        aceleracaoP.setText(bundle.getString("aceleracao"));



    }

    public void alterarPerformance(View v){
        try {
            bean = new Performance();

            bean.setId(bundle.getInt("_id"));

            bean.setIdU(Integer.valueOf(usuP.getText().toString()));
            bean.setIdC(Integer.valueOf(carP.getText().toString()));

            bean.setMotor(motorP.getText().toString());
            bean.setTipo(tipoP.getText().toString());
            bean.setPreco(Double.parseDouble(precoP.getText().toString()));
            bean.setAno(Double.parseDouble(anoP.getText().toString()));

            bean.setAceleracao(aceleracaoP.getText().toString());


            controller = new ControlePerformance(this);
            String resposta = controller.alterar(bean);
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            intent = new Intent(EditaPerformance.this, Menu.class);
            this.startActivity(intent);
            finish();
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarAlteracaoPerformance(View v) {
        intent = new Intent(EditaPerformance.this, Menu.class);
        this.startActivity(intent);
        finish();
    }
}