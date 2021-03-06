package com.ratboy.ratboy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.Serializable;

public class CadastroActivity extends AppCompatActivity {

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ImageButton salva = findViewById(R.id.iconeConfirma);
        salva.setOnClickListener(cliqueBotaoSalva());

        ImageButton cancela = findViewById(R.id.iconeCancela);
        cancela.setOnClickListener(cliqueBotaoCancela());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de produto");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent cadastroIt = getIntent();
        Serializable produtoS = cadastroIt.getSerializableExtra("produto");
        if(produtoS != null) {
            produto = (Produto)produtoS;
            EditText codigoProduto = (EditText) findViewById(R.id.campoCodigoProduto);
            EditText descricaoProduto = (EditText) findViewById(R.id.campoDescricaoProduto);
            EditText precoProduto = (EditText) findViewById(R.id.campoPrecoProduto);
            EditText quantidadeProduto = (EditText) findViewById(R.id.campoQuantidadeProduto);
            EditText observacaoProduto = (EditText) findViewById(R.id.campoObservacaoProduto);

            codigoProduto.setText(produto.codigo);
            descricaoProduto.setText(produto.descricao);
            precoProduto.setText(Double.toString(produto.preco));
            quantidadeProduto.setText(produto.quantidade);
            observacaoProduto.setText(produto.observacao);
        }
    }

    public View.OnClickListener cliqueBotaoSalva() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            EditText codigoProduto = (EditText) findViewById(R.id.campoCodigoProduto);
            EditText descricaoProduto = (EditText) findViewById(R.id.campoDescricaoProduto);
            EditText precoProduto = (EditText) findViewById(R.id.campoPrecoProduto);
            EditText quantidadeProduto = (EditText) findViewById(R.id.campoQuantidadeProduto);
            EditText observacaoProduto = (EditText) findViewById(R.id.campoObservacaoProduto);

            String textoCodigoProduto = codigoProduto.getText().toString();
            String textoDescricaoProduto = descricaoProduto.getText().toString();
            String textoPrecoProduto = precoProduto.getText().toString();
            String textoQuantidadeProduto = quantidadeProduto.getText().toString();
            String textoObservacaoProduto = observacaoProduto.getText().toString();

            if (produto == null) {
                produto = new Produto();
            }

            produto.codigo = textoCodigoProduto;
            produto.descricao = textoDescricaoProduto;
            produto.preco = Double.valueOf(textoPrecoProduto);
            produto.quantidade = textoQuantidadeProduto;
            produto.observacao = textoObservacaoProduto;

            ProdutoDB produtoDB = new ProdutoDB(CadastroActivity.this);

            produtoDB.save(produto);

            Intent returnIntent = new Intent();

            returnIntent.putExtra("produto",produto);

            setResult(Activity.RESULT_OK, returnIntent);
            finish();

            }
        };
    }

    public View.OnClickListener cliqueBotaoCancela() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Intent it = new Intent(CadastroActivity.this, CadastroActivity.class);
                finish();
                startActivityForResult(it, 1);
            }
        };
    }


}




