package com.example.carperformance.view.carro;

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
import com.example.carperformance.controller.ControleCarro;
import com.example.carperformance.view.Menu;

public class VerCarro extends AppCompatActivity {

    Intent intent;
    Carro bean;
    ControleCarro controller;
    TextView verId, verMarca, verModelo, verVelocidade;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carro);

        this.carregarDados();
    }

    public void carregarDados() {
        try {

            intent = getIntent();
            codigo = intent.getExtras().getInt("_id");

            verId = findViewById(R.id.verIdCarro);
            verMarca = findViewById(R.id.verMarcaCarro);
            verModelo = findViewById(R.id.verModeloCarro);
            verVelocidade = findViewById(R.id.verVelocidadeCarro);


            bean = new Carro();
            bean.setId(codigo);
            controller = new ControleCarro(getBaseContext());
            Cursor cursor = controller.buscar(bean);

            bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            verId.setText(verId.getText().toString() + bean.getId());
            bean.setMarca(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
            verMarca.setText(verMarca.getText().toString() + bean.getMarca());
            bean.setModelo(cursor.getString(cursor.getColumnIndexOrThrow("modelo")));
            verModelo.setText(verModelo.getText().toString() + bean.getModelo());
            bean.setVelocidade(cursor.getDouble(cursor.getColumnIndexOrThrow("velocidade")));
            verVelocidade.setText(verVelocidade.getText().toString() + bean.getVelocidade());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void editarCarro(View v) {
        intent = new Intent(VerCarro.this, EditaCarro.class);
        intent.putExtra("_id", bean.getId());
        intent.putExtra("marca", bean.getMarca());
        intent.putExtra("modelo", bean.getModelo());
        intent.putExtra("velocidade", bean.getVelocidade());

        this.startActivity(intent);
    }

    public void removerCarro(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover esse evento?")
                .setPositiveButton("SIM!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            bean = new Carro();
                            bean.setId(codigo);
                            controller = new ControleCarro(getBaseContext());
                            String resultado = controller.remover(bean);
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                            intent = new Intent(VerCarro.this, Menu.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            System.out.println("ERRO: " + e.getMessage());
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Carro removido com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void novaCarro(View v) {
        intent = new Intent(VerCarro.this, CriaCarro.class);
        this.startActivity(intent);
    }
}