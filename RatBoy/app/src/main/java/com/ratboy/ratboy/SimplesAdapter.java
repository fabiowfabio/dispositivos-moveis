package com.ratboy.ratboy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fabio on 15/04/2018.
 */

public class SimplesAdapter extends BaseAdapter {

    public class Produto {
        public String nome;
        public int imagem;
        public Produto (String nome, int imagem) {
            this.nome = nome;
            this.imagem = imagem;
        }
    }

    public Produto [] listaProdutos = new Produto [] {
            new Produto("Camiseta manga curta algodão com estampa", R.drawable.camiseta),
            new Produto("Camiseta manga curta algodão com bordado", R.drawable.camiseta),
            new Produto("Camiseta manga curta algodão com pedraria", R.drawable.camiseta),
            new Produto("Camiseta manga curta algodão com lantejoula", R.drawable.camiseta),
    };

    Context contexto;

    public SimplesAdapter (Context contexto) {
        this.contexto = contexto;

    }

    @Override
    public int getCount() {
        return listaProdutos.length;
    }

    @Override
    public Object getItem(int i) {
        return listaProdutos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Produto produtos = listaProdutos[i];
        View viewText = LayoutInflater.from(contexto) .inflate(R.layout.tela_inicial_itens, viewGroup, false);
        TextView t = viewText.findViewById(R.id.listaCamisetas);
        t.setText(produtos.nome);
        ImageView imagem = viewText.findViewById(R.id.imagemCamiseta);
        imagem.setImageResource(produtos.imagem);
        return viewText;
    }
}
