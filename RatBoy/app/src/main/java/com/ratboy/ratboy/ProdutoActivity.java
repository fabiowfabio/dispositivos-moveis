package com.ratboy.ratboy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


public class ProdutoActivity extends AppCompatActivity {

    private Produto produto;
    public static final int  RETORNO_ALTERA_ACTIVITY = 3;

    ImageView imagem;
    TextView codigo;
    TextView descricao;
    TextView preco;
    TextView quantidade;
    TextView observacao;

    byte [] byteImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Button botaoCamera = findViewById(R.id.botaoCamera);
        botaoCamera.setOnClickListener(cliqueBotaoCamera());

        Button botaoGaleria = findViewById(R.id.botaoGaleria);
        botaoGaleria.setOnClickListener(cliqueBotaoGaleria());

        Button botaoSalvaimagem = findViewById(R.id.botaoSalvarImagem);
        botaoSalvaimagem.setOnClickListener(cliqueBotaoSalvaImagem());


        // recuperar objeto do produto da Intent
        Intent it = getIntent();
        // variável da classe
        produto = (Produto)it.getSerializableExtra("produto");
        // finalize o código para preencher seus campos do layout
        // com os dados do Objeto Produto

        // preecher campos do layout



        imagem = (ImageView)findViewById(R.id.imagemProduto);
        codigo = (TextView)findViewById(R.id.codigoProduto);
        descricao = (TextView) findViewById(R.id.descricaoProduto);
        preco = (TextView)findViewById(R.id.precoProduto);
        quantidade = (TextView)findViewById(R.id.quantidadeProduto);
        observacao = (TextView)findViewById(R.id.observacaoProduto);
        //imagem = findViewById(R.id.imagemProduto);

        setProduto();
    }

    private void setProduto() {
        //Picasso.with(ProdutoActivity.this).load(produto.imagem).into(img);
        codigo.setText(produto.codigo);
        descricao.setText(produto.descricao);
        preco.setText(Double.toString(produto.preco));
        quantidade.setText(produto.quantidade);
        observacao.setText(produto.observacao);

        if(produto.imagem != null) {
            imagem.setImageBitmap(BitmapFactory.decodeByteArray(produto.imagem, 0, produto.imagem.length));
        }

    }



    public View.OnClickListener cliqueBotaoCamera() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        };
    }

    public View.OnClickListener cliqueBotaoGaleria() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        };
    }

    protected View.OnClickListener cliqueBotaoSalvaImagem() {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                produto.imagem = byteImages;
                ProdutoDB db= new ProdutoDB(ProdutoActivity.this);
                db.save(produto);
                finish();
            }
        };
    }



    @Override
    // Método para colocar o menu na ActionBar
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflar o menu na view
        getMenuInflater().inflate(R.menu.menu_produto, menu);
        return true;
    }

    // Tratar os eventos dos botões do menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_alterar) {
            Toast.makeText(ProdutoActivity.this, "Alterar",Toast.LENGTH_SHORT).show();
            Intent cadastroIt = new Intent(ProdutoActivity.this, CadastroActivity.class);
            cadastroIt.putExtra("produto", produto);
            startActivityForResult(cadastroIt, RETORNO_ALTERA_ACTIVITY);
        } else if (id == R.id.action_remover) {
            Toast.makeText(ProdutoActivity.this, "Remover", Toast.LENGTH_SHORT).show();
            ProdutoDB produtoDB = new ProdutoDB(ProdutoActivity.this);
            produtoDB.delete(produto);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("msg","Produto excluído com sucesso");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteImages = stream.toByteArray();
            ImageView imagem = (ImageView) findViewById(R.id.imagemProduto);
            imagem.setImageBitmap(imageBitmap);
        }
    }




}

