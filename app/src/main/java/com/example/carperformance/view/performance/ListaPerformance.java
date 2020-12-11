package com.example.carperformance.view.performance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carperformance.R;
import com.example.carperformance.bean.Performance;
import com.example.carperformance.controller.ControlePerformance;


public class ListaPerformance extends AppCompatActivity {

    ListView lista;
    Performance bean;
    ControlePerformance controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_performance);
        lista = (ListView) findViewById(R.id.listViewPerformance);
        this.carregarDados();
    }

    protected void carregarDados() {
        try {
            bean = new Performance();
            controller = new ControlePerformance(this);
            final Cursor cursor = controller.listar();
            String[] nomeCampos = {"_id", "id_usuario", "id_carro", "motor", "tipo","preco","ano","aceleracao"};
            int[] idViewLayout = {R.id.idPerformanceLayout, R.id.usuarioPerformanceLayout, R.id.carroPerformanceLayout, R.id.motorPerformanceLayout,R.id.anoPerformanceLayout,R.id.tipoPerformanceLayout,R.id.precoPerformanceLayout,R.id.aceleracaoPerformanceLayout};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.performance_layout, cursor, nomeCampos, idViewLayout, 0);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idx) {
                    int codigo;
                    cursor.moveToPosition(posicao);
                    codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent tela = new Intent(ListaPerformance.this, VerPerformance.class);
                    tela.putExtra("_id", codigo);
                    startActivity(tela);
                }
            });
        }
        catch(Exception e) {
            System.out.println("ERRO => " + e.getMessage());
        }
    }
}