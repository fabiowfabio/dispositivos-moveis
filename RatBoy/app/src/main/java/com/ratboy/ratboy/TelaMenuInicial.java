package com.ratboy.ratboy;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaMenuInicial extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu_inicial);

        ImageButton camiseta = (ImageButton) findViewById(R.id.iconeCamiseta);
        camiseta.setOnClickListener(cliqueCamiseta());

        ImageButton camera = (ImageButton) findViewById(R.id.iconeCamera);
        camera.setOnClickListener(cliqueCamera());

        //ImageButton shareItem = (ImageButton) findViewById(R.id.iconeCompartilhar);
        //ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        //share.setShareIntent(getDefautIntent());

        ImageButton sair = (ImageButton) findViewById(R.id.iconeSair);
        sair.setOnClickListener(cliqueSair());

    }

    protected View.OnClickListener cliqueCamiseta() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Intent it = new Intent(TelaMenuInicial.this, TelaListaProdutos.class);
                startActivityForResult(it, 1);
            }
        };
    }


    protected View.OnClickListener cliqueCamera() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Intent it = new Intent(TelaMenuInicial.this, TelaInicialActivity.class);
                startActivityForResult(it, 1);
            }
        };
    }


    public View.OnClickListener cliqueSair() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Saída do aplicativo");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        };
    }



}
