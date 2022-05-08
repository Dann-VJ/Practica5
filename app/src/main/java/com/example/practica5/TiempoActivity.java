package com.example.practica5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TiempoActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Timer temporizador;
    private TextView tvTiempo;
    private int contadorTiempo;

    private Button btnPlay;
    private Button btnPausa;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        temporizador = new Timer();
        tvTiempo = findViewById(R.id.tv_tiempo);
        contadorTiempo = 0;

        btnPlay = findViewById(R.id.btn_play);
        btnPausa = findViewById(R.id.btn_pausa);
        btnStop = findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temporizador.cancel();
                iniciaConteo();
            }
        });

        btnPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temporizador.cancel();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temporizador.cancel();
                contadorTiempo = 0;
                tvTiempo.setText(String.valueOf(contadorTiempo));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void iniciaConteo() {
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contadorTiempo ++;
                        tvTiempo.setText(String.valueOf(contadorTiempo));
                    }
                });
            }
        }, 1000, 1000);
    }
}