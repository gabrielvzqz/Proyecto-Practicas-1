package com.example.empresaclientes.Clientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.empresaclientes.R;

public class DetallesClientes extends AppCompatActivity {

    TextView tvID, tvNombre, tvEmpresa, tvTelefono, tvDNILetra, tvComunidad, tvProvincia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_clientes);

        tvID = findViewById(R.id.tvID);
        tvNombre = findViewById(R.id.tvNombre);
        tvEmpresa = findViewById(R.id.tvEmpresa);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvDNILetra = findViewById(R.id.tvDNILetra);
        tvComunidad = findViewById(R.id.tvComunidad);
        tvProvincia = findViewById(R.id.tvProvincia);

        // Obtener los datos del intent
        String id = getIntent().getStringExtra("ID");
        String nombre = getIntent().getStringExtra("Nombre");
        String empresa = getIntent().getStringExtra("Empresa");
        String telefono = getIntent().getStringExtra("Telefono");
        String dniLetra = getIntent().getStringExtra("DNIyLetra");
        String comunidad = getIntent().getStringExtra("Comunidad");
        String provincia = getIntent().getStringExtra("Provincia");

        // Mostrar los datos en los TextViews
        tvID.setText("ID: " + id);
        tvNombre.setText("Nombre: " + nombre);
        tvEmpresa.setText("Empresa: " + empresa);
        tvTelefono.setText("Teléfono: " + telefono);
        tvDNILetra.setText("DNI y Letra: " + dniLetra);
        tvComunidad.setText("Comunidad: " + comunidad);
        tvProvincia.setText("Provincia: " + provincia);

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad anterior
                finish();
            }
        });
    }
}