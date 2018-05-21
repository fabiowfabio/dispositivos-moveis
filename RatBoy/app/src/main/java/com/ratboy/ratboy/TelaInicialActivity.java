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
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

public class TelaInicialActivity extends DebugActivity {

    private List<Produto> produtos;
    private ListView lista;
    public static final int  RETORNO_PRODUTO_ACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Início");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // tela com a listView
        setContentView(R.layout.activity_tela_inicial);

        lista = (ListView)findViewById(R.id.listaElementos);

        // Criar objeto de ProdutoDB
        ProdutoDB produtoDB = new ProdutoDB(TelaInicialActivity.this);
        // Procurar produtos e armazenar na
        // variavel de classe produtos
        produtos = produtoDB.findAll();

        // Adapater de produtos
        lista.setAdapter(new ProdutoAdapter(TelaInicialActivity.this,produtos ));

        lista.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                // recuperar lista de produtos da listview
                List<Produto> listaFiltrada = ((ProdutoAdapter) adapterView.getAdapter()).getList();
                Intent produtoIntent = new Intent(TelaInicialActivity.this, ProdutoActivity.class);
                produtoIntent.putExtra("produto", listaFiltrada.get(index));
                // constante RETORNO_PRODUTO_ACTIVITY == 2
                startActivityForResult(produtoIntent, RETORNO_PRODUTO_ACTIVITY);
            }
        });

    }

    // Tratamento do evento de clique no botao de sair
    public View.OnClickListener cliqueSair() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Saída do Softwear");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                }
            };

        }

    @Override
    // Método para colocar o menu na ActionBar
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflar o menu na view
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Recuperar SearchView
        MenuItem item = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) item.getActionView();
        // Listener que espera a ação de buscar
        searchView.setOnQueryTextListener(onSearch());


        return true;
    }

    // Tratar os eventos dos botões do menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_buscar) {
            Toast.makeText(TelaInicialActivity.this, "Buscar", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Recuperar resultado de CadastroActivity após ela ser finalizada
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 || resultCode == RETORNO_PRODUTO_ACTIVITY) {
            ProdutoDB produtoDB = new ProdutoDB(TelaInicialActivity.this);
            produtos = produtoDB.findAll();
            lista.setAdapter(new ProdutoAdapter(TelaInicialActivity.this,produtos ));
        }
        if (requestCode == RETORNO_PRODUTO_ACTIVITY && resultCode == Activity.RESULT_OK) {
            String mensagem = data.getStringExtra("msg");
            Toast.makeText(TelaInicialActivity.this, mensagem, Toast.LENGTH_SHORT).show();
        }

    }

    // Funcao para retornar o tratamento do evento no SearchView
    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener() {
            @Override
            // Tratamento do evento quando termina de escrever
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase();
                Toast.makeText(TelaInicialActivity.this, query, Toast.LENGTH_SHORT).show();
                buscaProdutos(query);
                return false;
            }

            @Override
            // Tratamento do evento enquanto ainda está escrevendo
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(TelaInicialActivity.this, newText, Toast.LENGTH_SHORT).show();
                buscaProdutos(newText);
                return false;
            }
        };
    }

    private void buscaProdutos(String query) {
        List<Produto> results = new ArrayList<Produto>();
        for (Produto produto: produtos) {
            if(produto.descricao.toLowerCase().contains(query) || produto.codigo.contains(query)){
                results.add(produto);
            }
        }
        lista.setAdapter(new ProdutoAdapter(TelaInicialActivity.this,results));
    }











}
