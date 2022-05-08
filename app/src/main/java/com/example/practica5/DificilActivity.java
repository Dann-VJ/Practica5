package com.example.practica5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

public class DificilActivity extends AppCompatActivity {
    // ActionBar
    private ActionBar actionBar;

    // Time
    private Timer tempo;
    private TextView tvTime; // Texto del display 0
    private int contTiempo;
    private Button btnEmpezar; // Boton que inicializa
    private Button btnReiniciar; // Boton que reinicia el juego

    // Minijuego
    private ImageView ivRespuesta;
    private TextView tvCorazon;
    private TextView tvMensaje;
    private TextInputEditText tietNum;
    private Button btnAdivina;
    private String textCorazon = "";

    // Definimos la variable min y max para el modo fácil
    private final int min = 1;
    private final int max = 30;
    // Sacamos un número random de las variables min y max
    private final int num_adv = (int) Math.floor(Math.random() * (max - min + 1) + min);
    private int vidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificil);

        actionBar = getSupportActionBar();
        ivRespuesta = findViewById(R.id.iv_respuesta);
        tvCorazon = findViewById(R.id.tv_corazon);
        tvMensaje = findViewById(R.id.tv_mensaje);
        tietNum = findViewById(R.id.tiet_num);
        btnAdivina = findViewById(R.id.btn_adivina);
        btnReiniciar = findViewById(R.id.btn_reiniciar);

        // Definimos cuántas vidas disponibles tenemos
        vidas = 4;

        tempo = new Timer();
        tvTime = findViewById(R.id.tv_time);
        contTiempo = 60;

        btnEmpezar = findViewById(R.id.btn_empezar);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Mostramos un mensaje con el número correcto para pruebas
        //Snackbar.make(
        //        findViewById(android.R.id.content),
        //        "El número elegido es: " + num_adv,
        //        Snackbar.LENGTH_INDEFINITE
        //).setAction("OK", new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //    }
        //}).show();

        // Inicia el juego
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempo.cancel();
                iniciaConteo();

                btnAdivina.setEnabled( true );
            }
        });

        // Se reinicia el juego
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdivina.setEnabled( false );
                btnEmpezar.setEnabled( true );
                btnReiniciar.setEnabled( false );

                // Al dar click en el boton reiniciar se reinician las vidas
                vidas = 4;
                textCorazon = "♥♥♥♥";
                tvCorazon.setText(textCorazon);
                // Al dar click en el boton reiniciar se reinicia el tiempo
                contTiempo = 60;
                tvTime.setText(String.valueOf(contTiempo));
            }
        });

        btnAdivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Taraemos el nùmero seleccionado del btnAdivina
                int seleccion = Integer.parseInt(tietNum.getText().toString());
                // Hacemos la comparaciòn del nùmero seleccionado con el num_adv aleatorio

                if (seleccion == num_adv ){
                    // ivRespuesta.setImageDrawble(getResources().getDrawable(R.drawable.check));
                    tvMensaje.setText("!Felicidades ha encontrado el número: " + num_adv + "!");
                }
                else {
                    // Si el número adivinado es incorrecto se restan las vidas
                    vidas--;

                    if (vidas > 0) {
                        String numEncon = num_adv > seleccion ? "mayor" : "menor";
                        // Devuelve el texto poniendo si es mayor o menor que el número seleccionado
                        tvMensaje.setText("Número incorrecto tiene que ser " + numEncon + " a " + seleccion);

                        textCorazon = "";
                        for (int i = 1; i <= vidas; i++) {
                            textCorazon += "♥";
                        }
                        tvCorazon.setText(textCorazon);
                    }else {
                        tvMensaje.setText("GAME OVER");
                        textCorazon = "";
                        tvCorazon.setText(textCorazon);
                        btnEmpezar.setEnabled( false );
                        btnAdivina.setEnabled( false );
                        btnReiniciar.setEnabled( true );
                        tempo.cancel();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


        private void iniciaConteo () {
        tempo = new Timer();
        tempo.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (contTiempo > 0){
                            contTiempo--;
                            tvTime.setText(String.valueOf(contTiempo));
                        }else {
                            // Mostramos un mensaje
                            Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Se termino el tiempo",
                                    Snackbar.LENGTH_INDEFINITE
                            ).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                            // Cuando el tiepo se termino los botones se desabilitan
                            btnEmpezar.setEnabled( false );
                            btnAdivina.setEnabled( false );

                            // Y el boton de reiniciar se habilita para activar los botones
                            // Y el contador se reinicia
                            btnReiniciar.setEnabled( true );

                            tempo.cancel();

                        }
                    }
                });
            }
        }, 0, 1000);
    }
}