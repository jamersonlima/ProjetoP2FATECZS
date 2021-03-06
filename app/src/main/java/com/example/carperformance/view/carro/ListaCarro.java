package com.example.carperformance.view.carro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;


import com.example.carperformance.bean.Carro;
import com.example.carperformance.controller.ControleCarro;
import com.example.carperformance.R;


public class ListaCarro extends AppCompatActivity {

    ListView lista;
    Carro bean;
    ControleCarro controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carro);
        lista = (ListView) findViewById(R.id.listViewCarro);
        this.carregarDados();
    }

    protected void carregarDados() {
        try {
            bean = new Carro();
            controller = new ControleCarro(this);
            final Cursor cursor = controller.listar(bean);
            String[] nomeCampos = {"_id", "marca", "modelo", "velocidade"};
            int[] idViewLayout = {R.id.idCarroLayout, R.id.marcaCarroLayout, R.id.modeloCarroLayout, R.id.velocidadeCarroLayout};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.carro_layout, cursor, nomeCampos, idViewLayout, 0);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idx) {
                    int codigo;
                    cursor.moveToPosition(posicao);
                    codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent tela = new Intent(ListaCarro.this, VerCarro.class);
                    tela.putExtra("_id", codigo);
                    startActivity(tela);
                }
            });
        } catch (Exception e) {
            System.out.println("ERRO ---> " + e.getMessage());
        }
    }
}