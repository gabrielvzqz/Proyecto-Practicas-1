package com.example.empresaclientes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnadirCliente extends AppCompatActivity {


    EditText name, empresa, telefono;

    Button btnAdd;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        empresa = findViewById(R.id.empresa);
        telefono = findViewById(R.id.telefono);

        btnAdd = findViewById(R.id.btnAdd);

        DB = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String Nombre = name.getText().toString();
                String Empresa = empresa.getText().toString();
                String Telefono = telefono.getText().toString();

                Boolean checkinsertdata = DB.insertCliente(Nombre, Empresa, Telefono);
                if(checkinsertdata==true)
                    Toast.makeText(AnadirCliente.this, "New Client Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AnadirCliente.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}