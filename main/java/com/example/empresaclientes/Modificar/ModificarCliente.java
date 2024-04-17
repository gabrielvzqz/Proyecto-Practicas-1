package com.example.empresaclientes.Modificar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.Direccion.Comunidad;
import com.example.empresaclientes.Direccion.Provincia;
import com.example.empresaclientes.R;

import java.util.ArrayList;
import java.util.List;

public class ModificarCliente extends AppCompatActivity {

    private String selectedComunidad;
    private Spinner comunidadSpinner, provinciaSpinner;
    private ArrayAdapter<CharSequence> seleccionarAdapter;
    private ArrayAdapter<Provincia> provinciaAdapter;
    TextView idAdmin;
    EditText nombre, empresa, telefono, dni;
    Button modificar;
    DBHelper DB;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_cliente);

        comunidadSpinner = findViewById(R.id.spinnerComunidades);
        List<Comunidad> listaComunidadesAutonomas = llenarComunidades();
        ArrayAdapter<Comunidad> comunidadAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaComunidadesAutonomas);
        comunidadSpinner.setAdapter(comunidadAdapter);

        provinciaSpinner = findViewById(R.id.spinnerProvincias);

        comunidadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedComunidad = comunidadAdapter.getItem(position).getComunidad();

                switch (selectedComunidad) {
                    case "Selecciona Comunidad Autónoma":
                        seleccionarAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_provincias_predeterminadas, R.layout.spinner_layout);
                        provinciaSpinner.setAdapter(seleccionarAdapter);
                        break;
                    case "Andalucía":
                    case "Aragón":
                    case "Castilla y León":
                    case "Asturias":
                    case "Islas Baleares":
                    case "Canarias":
                    case "Cantabria":
                    case "Castilla-La Mancha":
                    case "Cataluña":
                    case "Comunidad Valenciana":
                    case "Extremadura":
                    case "Galicia":
                    case "La Rioja":
                    case "Madrid":
                    case "Murcia":
                    case "Navarra":
                    case "País Vasco":
                    case "Ceuta":
                    case "Melilla":
                        List<Provincia> listaProvincias = llenarProvincias(selectedComunidad);
                        provinciaAdapter = new ArrayAdapter<>(getApplicationContext(),
                                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaProvincias);
                        provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        provinciaSpinner.setAdapter(provinciaAdapter);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        idAdmin = findViewById(R.id.textIDAdmin);
        nombre = findViewById(R.id.etNombreAdmin);
        empresa = findViewById(R.id.etEmpresaAdmin);
        telefono = findViewById(R.id.etTelefonoAdmin);
        dni = findViewById(R.id.etDNILetraAdmin);
        comunidadSpinner = findViewById(R.id.spinnerComunidades);
        provinciaSpinner = findViewById(R.id.spinnerProvincias);

        modificar = findViewById(R.id.btnModificar);

        DB = new DBHelper(this);

        String id = getIntent().getStringExtra("ID");
        idAdmin.setText(String.valueOf(id));
        String Nombre = nombre.getText().toString();
        String Empresa = empresa.getText().toString();
        String Telefono = telefono.getText().toString();
        String Dni = dni.getText().toString();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos del cliente
                Integer Id = Integer.parseInt(idAdmin.getText().toString());
                String Nombre = nombre.getText().toString();
                String Empresa = empresa.getText().toString();
                Integer Telefono = Integer.parseInt(telefono.getText().toString());
                String Dni = dni.getText().toString();
                String Comunidad = comunidadSpinner.getSelectedItem().toString();
                String Provincia = provinciaSpinner.getSelectedItem().toString();

                Boolean checkupdatedata = DB.updatedata(Id, Nombre, Empresa, Telefono, Dni, Comunidad, Provincia);
                if(checkupdatedata==true) {
                    Toast.makeText(ModificarCliente.this, "Client Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModificarCliente.this, ListViewActivityModify.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(ModificarCliente.this, "Client Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ModificarCliente.this, ListViewActivityModify.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("Range")
    private List<Provincia> llenarProvincias(String comunidad) {
        List<Provincia> listProvincias = new ArrayList<>();
        DBHelper DB = new DBHelper(ModificarCliente.this);
        Cursor cursor = DB.mostrarProvincias(comunidad);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Provincia provincia = new Provincia();
                provincia.setProvincia(cursor.getString(cursor.getColumnIndex("Nombre")));
                listProvincias.add(provincia);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();

        return listProvincias;
    }

    @SuppressLint("Range")
    private List<Comunidad> llenarComunidades() {
        List<Comunidad> listComunidades = new ArrayList<>();
        DBHelper DB = new DBHelper(ModificarCliente.this);
        Cursor cursor = DB.mostrarComunidades();
        if (cursor != null && cursor.moveToFirst()) {

            do {
                Comunidad comunidades = new Comunidad();
                comunidades.setComunidad(cursor.getString(cursor.getColumnIndex("Nombre")));
                listComunidades.add(comunidades);
            } while (cursor.moveToNext());
        }
        DB.close();

        return listComunidades;
    }
}