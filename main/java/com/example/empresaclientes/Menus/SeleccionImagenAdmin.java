package com.example.empresaclientes.Menus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.ClientesAdmin.ClientesAdmin;
import com.example.empresaclientes.Modificar.ListViewActivityModify;
import com.example.empresaclientes.ProductosAdmin.ProductosAdmin;
import com.example.empresaclientes.R;
import com.example.empresaclientes.VentasAdmin.ListViewActivityVentasAdmin;

public class SeleccionImagenAdmin extends AppCompatActivity {

    ImageButton imagen1, imagen2, imagen3, imagen4;
    Button boton1, boton2, boton3, boton4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_imagen_admin);


        boton1 = findViewById(R.id.buttonVentas);
        boton2 = findViewById(R.id.buttonProductos);
        boton3 = findViewById(R.id.buttonClientes);
        boton4 = findViewById(R.id.buttonModificar);

        imagen1 = findViewById(R.id.imageButtonVentas);
        imagen2 = findViewById(R.id.imageButtonProductos);
        imagen3 = findViewById(R.id.imageButtonClientes);
        imagen4 = findViewById(R.id.imageButtonModificar);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityVentasAdmin.class);
                startActivity(intent);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductosAdmin.class);
                startActivity(intent);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientesAdmin.class);
                startActivity(intent);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityModify.class);
                startActivity(intent);
            }
        });
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityVentasAdmin.class);
                startActivity(intent);
            }
        });
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductosAdmin.class);
                startActivity(intent);
            }
        });
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientesAdmin.class);
                startActivity(intent);
            }
        });
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivityModify.class);
                startActivity(intent);
            }
        });
    }
}