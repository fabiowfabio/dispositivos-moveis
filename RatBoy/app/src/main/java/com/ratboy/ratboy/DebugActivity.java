package com.ratboy.ratboy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MY_APP", getClassName() + " chamou OnCreate()chamado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY_APP", getClassName() + "chamou OnStart()chamdo");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MY_APP", getClassName() + " chamou OnRestart()chamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY_APP", getClassName() + " chamou OnResume()chamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY_APP", getClassName() + " chamou OnPause()chamado");
    }

    @Override
    protected  void onStop() {
        super.onStop();
        Log.i("MY_APP", getClassName() + " chamou OnStop()chamado");
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.i("MY_APP", getClassName() + " chamou OnDestroy()chamado");
    }

    private String getClassName() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }

}
