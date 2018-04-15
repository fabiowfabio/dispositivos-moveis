package com.ratboy.ratboy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by fabio on 15/04/2018.
 */

public class SimplesAdapter extends BaseAdapter {

    public String [] listaProdutos = new String [] {
            "Camiseta manga curta algodão com estampa",
            "Camiseta manga curta algodão com bordado",
            "Camiseta manga curta algodão com pedraria",
            "Camiseta manga curta algodão com lantejoula",
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
    public View getView(int i, View convertView, ViewGroup parent) {
        String produtos = listaProdutos[i];
        TextView t = new TextView(contexto);
        t.setText(produtos);
        return t;
    }
}
