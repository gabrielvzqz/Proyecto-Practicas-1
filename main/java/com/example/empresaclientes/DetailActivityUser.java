package com.example.empresaclientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivityUser extends AppCompatActivity {

    TextView tvID, tvNombre, tvEmpresa, tvTelefono;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        tvID = findViewById(R.id.tvID);
        tvNombre = findViewById(R.id.tvNombre);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        tvTelefono = findViewById(R.id.tvTelefono);

        // Obtener los datos del intent
        String id = getIntent().getStringExtra("ID");
        String nombre = getIntent().getStringExtra("Nombre");
        String empresa = getIntent().getStringExtra("Empresa");
        String telefono = getIntent().getStringExtra("Telefono");

        // Mostrar los datos en los TextViews
        tvID.setText("ID: " + id);
        tvNombre.setText("Nombre: " + nombre);
        tvEmpresa.setText("Empresa: " + empresa);
        tvTelefono.setText("Teléfono: " + telefono);
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad anterior
                finish();
            }
        });

        Button btnNuevaPantalla = findViewById(R.id.btnNuevaPantalla);
        btnNuevaPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad al hacer clic en el botón
                Intent intent = new Intent(DetailActivityUser.this, Productos.class);
                startActivity(intent);
            }
        });
    }
}