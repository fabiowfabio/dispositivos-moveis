package com.ratboy.ratboy;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProdutoActivity extends AppCompatActivity {

    private Produto produto;
    public static final int  RETORNO_ALTERA_ACTIVITY = 3;

    ImageView img;
    TextView codigo;
    TextView descricao;
    TextView preco;
    TextView quantidade;
    TextView observacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        // recuperar objeto do produto da Intent
        Intent it = getIntent();
        // variável da classe
        produto = (Produto)it.getSerializableExtra("produto");
        // finalize o código para preencher seus campos do layout
        // com os dados do Objeto Produto

        // preecher campos do layout

        img = (ImageView)findViewById(R.id.imagemProduto);
        codigo = (TextView)findViewById(R.id.codigoProduto);
        descricao = (TextView) findViewById(R.id.descricaoProduto);
        preco = (TextView)findViewById(R.id.precoProduto);
        quantidade = (TextView)findViewById(R.id.quantidadeProduto);
        observacao = (TextView)findViewById(R.id.observacaoProduto);

        setProduto();

    }

    private void setProduto() {
        //Picasso.with(ProdutoActivity.this).load(produto.imagem).into(img);
        codigo.setText(produto.codigo);
        descricao.setText(produto.descricao);
        preco.setText(Double.toString(produto.preco));
        quantidade.setText(produto.quantidade);
        observacao.setText(produto.observacao);

    }



}

