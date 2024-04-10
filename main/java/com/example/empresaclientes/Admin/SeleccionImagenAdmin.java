package com.example.empresaclientes.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.Clientes.Clientes;
import com.example.empresaclientes.Productos.Productos;
import com.example.empresaclientes.R;

public class SeleccionImagenAdmin extends AppCompatActivity {

    ImageButton imagen1, imagen2, imagen3;
    Button boton1, boton2, boton3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_imagen_admin);


        boton1 = findViewById(R.id.buttonVentas);
        boton2 = findViewById(R.id.buttonProductos);
        boton3 = findViewById(R.id.buttonClientes);

        imagen1 = findViewById(R.id.imageButtonVentas);
        imagen2 = findViewById(R.id.imageButtonProductos);
        imagen3 = findViewById(R.id.imageButtonClientes);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityAdmin.class);
                startActivity(intent);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Clientes.class);
                startActivity(intent);
            }
        });
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityAdmin.class);
                startActivity(intent);
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Clientes.class);
                startActivity(intent);
            }
        });
    }
}