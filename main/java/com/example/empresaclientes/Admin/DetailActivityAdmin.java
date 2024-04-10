package com.example.empresaclientes.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.Productos.Productos;
import com.example.empresaclientes.R;

public class DetailActivityAdmin extends AppCompatActivity {

    TextView TvID, TvNombre, TvEmpresa, TvTelefono, TvDNILetra, TvComunidad, TvProvincia;
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
        TvDNILetra = findViewById(R.id.tvDNILetraAdmin);
        TvComunidad = findViewById(R.id.tvComunidadAdmin);
        TvProvincia = findViewById(R.id.tvProvinciaAdmin);

        // Obtener los datos del intent
        String id = getIntent().getStringExtra("ID");
        String nombre = getIntent().getStringExtra("Nombre");
        String empresa = getIntent().getStringExtra("Empresa");
        String telefono = getIntent().getStringExtra("Telefono");
        String dniLetra = getIntent().getStringExtra("DNIyLetra");
        String comunidad = getIntent().getStringExtra("Comunidad");
        String provincia = getIntent().getStringExtra("Provincia");

        // Mostrar los datos en los TextViews
        TvID.setText(id);
        TvNombre.setText(nombre);
        TvEmpresa.setText(empresa);
        TvTelefono.setText(telefono);
        TvDNILetra.setText(dniLetra);
        TvComunidad.setText(comunidad);
        TvProvincia.setText(provincia);



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
                String id = TvID.getText().toString(); // Obtener el ID en lugar del nombre
                Boolean checkdeletedata = DB.deletedata(id); // Llamar al método de eliminación con el ID
                if (checkdeletedata) {
                    Toast.makeText(DetailActivityAdmin.this, "Client Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivityAdmin.this, ListViewActivityAdmin.class);
                    startActivity(intent);
                } else {
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
                // Obtener los datos del cliente
                String id = TvID.getText().toString();
                String nombre = TvNombre.getText().toString();
                String empresa = TvEmpresa.getText().toString();
                String telefono = TvTelefono.getText().toString();
                String dni = TvDNILetra.getText().toString();
                String comunidad = TvComunidad.getText().toString();
                String provincia = TvProvincia.getText().toString();

                // Pasar los datos a la actividad ModificarCliente
                Intent intent = new Intent(DetailActivityAdmin.this, ModificarCliente.class);
                intent.putExtra("ID", id);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("Empresa", empresa);
                intent.putExtra("Telefono", telefono);
                intent.putExtra("DNI_Letra", dni);
                intent.putExtra("Comunidad", comunidad);
                intent.putExtra("Provincia", provincia);
                startActivity(intent);
            }
        });

    }
}