package com.ratboy.ratboy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;




public class ProdutoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Produto> listaProdutos;

    public ProdutoAdapter(Context context, List<Produto> listaProdutos) {
        this.context = context;
        this.listaProdutos = listaProdutos;
    }

    @Override
    public int getCount() {
        return listaProdutos != null? listaProdutos.size():0;
    }

    @Override
    public Object getItem(int posicao) {
        return listaProdutos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        // Infla a View
        View viewText = LayoutInflater.from(context).inflate(R.layout.tela_inicial_itens, viewGroup, false);
        // Procura elementos de tela para atualizar
        TextView t = (TextView)viewText.findViewById(R.id.listaCamisetas);
      // atualiza valores da view
         ImageView img = (ImageView)viewText.findViewById(R.id.imagemCamiseta);
        Produto produto = listaProdutos.get(posicao);
        t.setText("Código: " + produto.codigo + "\n" +
                "Descrição: " + produto.descricao + "\n" +
                "Preço: R$"+ produto.preco + "\n" );

        //if(!produto.imagem.isEmpty()) {
            //Picasso.with(context).load(produto.imagem).into(img);
        //}


        return viewText;
    }

    public List<Produto> getList(){
        return listaProdutos;
    }


}