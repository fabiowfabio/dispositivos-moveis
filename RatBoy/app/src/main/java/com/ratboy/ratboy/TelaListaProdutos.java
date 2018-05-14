package com.ratboy.ratboy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TelaListaProdutos extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_produtos);

        ListView lista = findViewById(R.id.listaProdutos);
        lista.setAdapter(new SimplesAdapter(TelaListaProdutos.this));

        lista.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TelaListaProdutos.this, "clicou " + i, Toast.LENGTH_SHORT).show();
            }
        });

    }



}
