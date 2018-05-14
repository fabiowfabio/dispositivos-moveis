package com.ratboy.ratboy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDB extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "softwear.sqlite";
    public static final int VERSAO_BANCO = 2;

    public ProdutoDB(Context context){
         super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists produto (" +
                "_id integer primary key autoincrement, " +
                "codigo text, descricao text, preco real, " +
                "quantidade text, observacao text);");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // método para executar quando a versão do BD for alterada
        if (versaoAntiga == 1 && versaoNova == 2) {
            db.execSQL("alter table produto add column imagem BLOB;");
        }
    }

    // inserir uma novo produto ou atualizar existente
    public long save(Produto produto){
        long id = produto.id;
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("codigo", produto.codigo);
            values.put("descricao", produto.descricao);
            values.put("preco", produto.preco);
            values.put("quantidade", produto.quantidade);
            values.put("observacao", produto.observacao);
            values.put("imagem", produto.imagem);

            if (id != 0) {
                String _id = String.valueOf(id);
                String [] argsFiltro = new String[]{_id};
                // criar update produto set values = ... where _id = ?
                int count = db.update("produto", values, "_id=?", argsFiltro);
                return count;
            } else {
                // criar insert into produto values (...)
                id = db.insert("produto","", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    // remover produto
    public int delete(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        try{
            // criar delete from produto where _id = ?
            int count = db.delete("produto", "_id=?", new String[]{String.valueOf(produto.id)});
            return count;
        } finally {
            db.close();
        }
    }

    // retornar todos os produtos
    public List<Produto> findAll() {
        SQLiteDatabase db =  getWritableDatabase();
        try {
            // criar select * from produto
            Cursor c = db.query("produto", null, null, null, null, null, null, null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    // lê o cursor e cria a lista de produtos
    private List<Produto> toList(Cursor c) {
        List<Produto> produtos = new ArrayList<Produto>();
        if (c.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produtos.add(produto);
                produto.id = c.getLong(c.getColumnIndex("_id"));
                produto.codigo = c.getString(c.getColumnIndex("codigo"));
                produto.descricao = c.getString(c.getColumnIndex("descricao"));
                produto.preco = c.getDouble(c.getColumnIndex("preco"));
                produto.quantidade = c.getString(c.getColumnIndex("quantidade"));
                produto.observacao = c.getString(c.getColumnIndex("observacao"));
                produto.imagem = c.getBlob(c.getColumnIndex("imagem"));

            } while (c.moveToNext());
        }
        return produtos;
    }

    // executa um SQL qualquer
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    // executa um SQL qualquer, com parâmetros
    public void execSQL(String sql, Object [] args) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql, args);
        }finally {
            db.close();
        }
    }
}
