package com.example.carperformance.view.performance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carperformance.R;
import com.example.carperformance.bean.Carro;
import com.example.carperformance.bean.Performance;
import com.example.carperformance.controller.ControlePerformance;
import com.example.carperformance.view.Menu;

public class VerPerformance extends AppCompatActivity {

    Intent intent;
    Performance bean;
    ControlePerformance controller;
    TextView verId, usu, car, motor, tipo, preco, aceleracao, ano;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_performance);

        this.carregarDados();
    }

    public void carregarDados() {
        try {

            intent = getIntent();
            codigo = intent.getExtras().getInt("_id");

            verId = findViewById(R.id.verIdPerformance);
            usu = findViewById(R.id.verUsuarioPerformance);
            car = findViewById(R.id.verCarroPerformance);
            //marca = findViewById(R.id.verMarcaPerformance);
            //modelo = findViewById(R.id.verModeloPerformance);
            tipo = findViewById(R.id.verTipoPerformance);
            motor= findViewById(R.id.verMotorPerformance);
            preco = findViewById(R.id.verPrecoPerformance);
            ano = findViewById(R.id.verAnoPerformance);
            //velocidade = findViewById(R.id.verVelocidadePerformance);
            aceleracao = findViewById(R.id.verAceleracaoPerformance);




            bean = new Performance();
            bean.setId(codigo);
            controller = new ControlePerformance(getBaseContext());
            Cursor cursor = controller.buscar(bean);

            bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            verId.setText(verId.getText().toString() + bean.getId());

            bean.setIdU(cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario")));
            usu.setText(usu.getText().toString() + bean.getIdU());

            bean.setIdC(cursor.getInt(cursor.getColumnIndexOrThrow("id_carro")));
            car.setText(car.getText().toString() + bean.getIdC());

            bean.setMotor(cursor.getString(cursor.getColumnIndexOrThrow("motor")));
            motor.setText(motor.getText().toString() + bean.getMotor());

            bean.setTipo(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
            tipo.setText(tipo.getText().toString() + bean.getTipo());

            bean.setPreco(cursor.getDouble(cursor.getColumnIndexOrThrow("preco")));
            preco.setText(preco.getText().toString() + bean.getPreco());

            bean.setAno(cursor.getDouble(cursor.getColumnIndexOrThrow("ano")));
            ano.setText(ano.getText().toString() + bean.getAno());

            bean.setAceleracao(cursor.getString(cursor.getColumnIndexOrThrow("aceleracao")));
            aceleracao.setText(aceleracao.getText().toString() + bean.getAceleracao());



        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void editarPerformance(View v) throws Exception {
        intent = new Intent(VerPerformance.this, EditaPerformance.class);

        intent.putExtra("_id", bean.getId());
        intent.putExtra("id_usuario", bean.getIdU());
        intent.putExtra("id_carro", bean.getIdC());
        intent.putExtra("motor", bean.getMotor());
        intent.putExtra("tipo", bean.getTipo());
        intent.putExtra("preco", bean.getPreco());
        intent.putExtra("ano", bean.getAno());
        intent.putExtra("aceleracao", bean.getAceleracao());

        this.startActivity(intent);
    }

    public void removerPerformance(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover essa performance?")
                .setPositiveButton("SIM!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        try {
                            bean = new Performance();
                            bean.setId(codigo);
                            controller = new ControlePerformance(getBaseContext());
                            String resultado = controller.remover(bean);
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                            intent = new Intent(VerPerformance.this, Menu.class);
                            startActivity(intent);
                        }
                        catch (Exception e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Performance removida com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void novaPerformance(View v) {
        intent = new Intent(VerPerformance.this, CriaPerformance.class);
        this.startActivity(intent);
    }
}