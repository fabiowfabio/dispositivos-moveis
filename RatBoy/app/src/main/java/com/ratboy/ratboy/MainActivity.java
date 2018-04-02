package com.ratboy.ratboy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button botao = (Button) findViewById(R.id.botao_login);
        botao.setOnClickListener(clique_login());
    }

    protected View.OnClickListener clique_login() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this, TelaInicialActivity.class);
                startActivityForResult(it, 1);
            }
        };
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            String result = data.getStringExtra("result");
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }



}
