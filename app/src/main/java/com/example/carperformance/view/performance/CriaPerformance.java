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

public class CriaPerformance extends AppCompatActivity {

    EditText usu, car, motor, tipo, preco, ano, aceleracao;

    Performance bean;
    ControlePerformance controller;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_performance);

        usu = (EditText) findViewById(R.id.usuTxt);
        car = (EditText) findViewById(R.id.carTxt);
        motor = (EditText) findViewById(R.id.motorTxt);
        tipo= (EditText) findViewById(R.id.tipoTxt);
        preco= (EditText) findViewById(R.id.precoTxt);
        ano= (EditText) findViewById(R.id.anoTxt);
        aceleracao= (EditText) findViewById(R.id.aceleracaoTxt);

    }

    public void cadastrarPerformance(View v) {
        try {
            bean = new Performance();

            bean.setIdU(Integer.valueOf(usu.getText().toString()));
            bean.setIdC(Integer.valueOf(car.getText().toString()));

            bean.setMotor(motor.getText().toString());
            bean.setTipo(tipo.getText().toString());
            bean.setPreco(Double.parseDouble(preco.getText().toString()));
            bean.setAno(Double.parseDouble(ano.getText().toString()));

            bean.setAceleracao(aceleracao.getText().toString());


            controller = new ControlePerformance(this);
            String resultado = controller.inserir(bean);


            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            intent = new Intent(CriaPerformance.this, Menu.class);
            this.startActivity(intent);
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarCadastroPerformance(View v) {
        super.onBackPressed();
        finish();
    }
}