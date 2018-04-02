package com.ratboy.ratboy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ImageView imagem = (ImageView) findViewById(R.id.imagem_tela_inicial);
        imagem.setImageResource(R.drawable.img_login);

        TextView texto_inicial = (TextView) findViewById(R.id.textView);
        texto_inicial.setText("Bem vindo!");

        Button botao = (Button) findViewById(R.id.botao_login);
        botao.setOnClickListener(clique_login());
    }

    protected View.OnClickListener clique_login() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                Intent it = new Intent(MainActivity.this, TelaInicialActivity.class);
                startActivity(it);
            }
        };

    }
}
