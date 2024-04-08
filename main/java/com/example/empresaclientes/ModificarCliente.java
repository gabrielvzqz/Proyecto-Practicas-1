package com.example.empresaclientes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarCliente extends AppCompatActivity {

    TextView idAdmin;
    EditText nombre, empresa, telefono;
    Button modificar;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_cliente);

        idAdmin = findViewById(R.id.textIDAdmin);
        nombre = findViewById(R.id.etNombreAdmin);
        empresa = findViewById(R.id.etEmpresaAdmin);
        telefono = findViewById(R.id.etTelefonoAdmin);

        modificar = findViewById(R.id.btnModificar);

        DB = new DBHelper(this);

        String id = getIntent().getStringExtra("ID");
        idAdmin.setText(String.valueOf(id));

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer Id = Integer.parseInt(idAdmin.getText().toString());
                String Nombre = nombre.getText().toString();
                String Empresa = empresa.getText().toString();
                Integer Telefono = Integer.parseInt(telefono.getText().toString());

                Boolean checkupdatedata = DB.updatedata(Id, Nombre, Empresa, Telefono);
                if(checkupdatedata==true) {
                    Toast.makeText(ModificarCliente.this, "Client Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModificarCliente.this, ListViewActivityAdmin.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(ModificarCliente.this, "Client Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        Button btnVolverlistView = findViewById(R.id.btnVolverListView);
        btnVolverlistView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ModificarCliente.this, ListViewActivityAdmin.class);
                startActivity(intent);
                finish();  // Finalizar la actividad actual
            }
        });
    }
}