package com.example.empresaclientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivityAdmin extends AppCompatActivity {

    TextView TvID, TvNombre, TvEmpresa, TvTelefono;
    DBHelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

        DB = new DBHelper(this);  // Inicializar DB correctamente
        TvID = findViewById(R.id.tvIDAdmin);
        TvNombre = findViewById(R.id.tvNombreAdmin);
        TvEmpresa = findViewById(R.id.tvEmpresaAdmin);
        TvTelefono = findViewById(R.id.tvTelefonoAdmin);

        // Obtener los datos del intent
        String id = getIntent().getStringExtra("ID");
        String nombre = getIntent().getStringExtra("Nombre");
        String empresa = getIntent().getStringExtra("Empresa");
        String telefono = getIntent().getStringExtra("Telefono");

        // Mostrar los datos en los TextViews
        TvID.setText(id);
        TvNombre.setText(nombre);
        TvEmpresa.setText(empresa);
        TvTelefono.setText(telefono);


        Button btnVolver = findViewById(R.id.btnVolverAdmin);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad anterior
                finish();
            }
        });

        Button btnNuevaPantalla = findViewById(R.id.btnNuevaPantallaAdmin);
        btnNuevaPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad al hacer clic en el botón
                Intent intent = new Intent(DetailActivityAdmin.this, Productos.class);
                startActivity(intent);
            }
        });

        Button btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = TvNombre.getText().toString().trim();
                Boolean checkdeletedata =DB.deletedata(Nombre);
                if(checkdeletedata){
                    Toast.makeText(DetailActivityAdmin.this, "Client Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivityAdmin.this, ListViewActivityAdmin.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(DetailActivityAdmin.this, "Client Not Deleted", Toast.LENGTH_SHORT).show();
                }
                // Volver a la actividad anterior
                finish();
            }
        });

        Button btnModificar = findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad al hacer clic en el botón
                String id = TvID.getText().toString();
                String nombre = TvNombre.getText().toString();
                String empresa = TvEmpresa.getText().toString();
                String telefono = TvTelefono.getText().toString();

                // Pasar los datos a la actividad ModificarCliente
                Intent intent = new Intent(DetailActivityAdmin.this, ModificarCliente.class);
                intent.putExtra("ID", id);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("Empresa", empresa);
                intent.putExtra("Telefono", telefono);
                startActivity(intent);
            }
        });
    }
}