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

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class FacilActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private ImageView ivRespuesta;
    private TextView tvCorazon;
    private TextView tvMensaje;
    private TextInputEditText tietNum;
    private Button btnAdivina;
    private String textCorazon = "";

    // Definimos la variable min y max para el modo fácil
    private final int min = 1;
    private final int max = 10;
    // Sacamos un número random de las variables min y max
    private final int num_adv = (int) Math.floor(Math.random() * (max - min + 1) + min);
    private int vidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);

        actionBar = getSupportActionBar();
        ivRespuesta = findViewById(R.id.iv_respuesta);
        tvCorazon = findViewById(R.id.tv_corazon);
        tvMensaje = findViewById(R.id.tv_mensaje);
        tietNum = findViewById(R.id.tiet_num);
        btnAdivina = findViewById(R.id.btn_adivina);
        // Definimos cuántas vidas disponibles tenemos
        vidas = 3;

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Mostramos un mensaje
        /*
        Snackbar.make(
                findViewById(android.R.id.content),
                "El número elegido es: " + num_adv,
                Snackbar.LENGTH_INDEFINITE
        ).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
         */

        btnAdivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Traemos el nùmero seleccionado del btnAdivina
                int seleccion = Integer.parseInt(tietNum.getText().toString());
                // Hacemos la comparaciòn del nùmero seleccionado con el num_adv aleatorio
                if (seleccion == num_adv){
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
                        tvMensaje.setText("Game Over");
                        textCorazon = "";
                        tvCorazon.setText(textCorazon);
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
}