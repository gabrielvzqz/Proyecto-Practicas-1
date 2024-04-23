package com.example.empresaclientes.ClientesAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.R;

public class DetallesClientesAdmin extends AppCompatActivity {

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
        String dniLetra = getIntent().getStringExtra("DNI_Letra");
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

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(DetallesClientesAdmin.this, ClientesAdmin.class);
                startActivity(intent);
            }
        });
    }
}