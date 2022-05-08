package com.example.practica5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnFacil;
    private Button btnDificil;
    private Button btnReglas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnFacil = findViewById(R.id.btn_facil);
        btnDificil = findViewById(R.id.btn_dificil);
        btnReglas = findViewById(R.id.btn_reglas);

        // Nos lleva al activity modo facil
        btnFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linearIntent = new Intent(
                        MenuActivity.this,
                        FacilActivity.class
                );
                startActivity(linearIntent);
            }
        });

        // Nos lleva al activity modo dificil
        btnDificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linearIntent = new Intent(
                        MenuActivity.this,
                        DificilActivity.class
                );
                startActivity(linearIntent);
            }
        });

        // Nos lleva al activity modo dificil
        btnReglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linearIntent = new Intent(
                        MenuActivity.this,
                        ReglasActivity.class
                );
                startActivity(linearIntent);
            }
        });

    }
}