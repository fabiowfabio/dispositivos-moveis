package com.ratboy.ratboy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class TelaInicialActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Início");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button botao_sair = (Button) findViewById(R.id.botao_sair);
        botao_sair.setOnClickListener(clickSair());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(onSearch());
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        share.setShareIntent(getDefautIntent());


        return true;
    }

    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(TelaInicialActivity.this, "Resultado da busca", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(TelaInicialActivity.this, "Digite a palavra chave", Toast.LENGTH_SHORT).show();
                return false;
            }
        };
    }

    private Intent getDefautIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Código do produto");
        return intent;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_atualizar) {
            Toast.makeText(TelaInicialActivity.this,"Atualizar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_buscar) {
            Toast.makeText(TelaInicialActivity.this, "Buscar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_adicionar) {
            Toast.makeText(TelaInicialActivity.this, "Adicionar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_config) {
            Toast.makeText(TelaInicialActivity.this, "Configurar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_share) {
            Toast.makeText(TelaInicialActivity.this, "Compartilhar", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }




    public View.OnClickListener clickSair() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Saída do aplicativo");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        };
    }


}
