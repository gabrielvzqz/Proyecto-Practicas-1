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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.Direccion.Comunidad;
import com.example.empresaclientes.Direccion.Provincia;
import com.example.empresaclientes.R;

import java.util.ArrayList;
import java.util.List;

// ... (importaciones y declaraciones anteriores)

public class AnadirCliente extends AppCompatActivity {

    private String selectedComunidad, selectedProvincias;
    private Spinner comunidadSpinner, provinciaSpinner;
    private ArrayAdapter<CharSequence> seleccionarAdapter;
    private ArrayAdapter<Provincia> provinciaAdapter;

    EditText name, empresa, telefono, dniLetra;

    Button btnAdd;
    DBHelper DB;

    @SuppressLint({"MissingInflatedId", "WrongViewCast", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_cliente);

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

        name = findViewById(R.id.name);
        empresa = findViewById(R.id.empresa);
        telefono = findViewById(R.id.telefono);
        dniLetra = findViewById(R.id.dni);

        btnAdd = findViewById(R.id.btnAdd);

        DB = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String DNI_Letra = dniLetra.getText().toString();
                String Nombre = name.getText().toString();
                String Empresa = empresa.getText().toString();
                String Telefono = telefono.getText().toString();
                String Comunidad = comunidadSpinner.getSelectedItem().toString();
                String Provincia = provinciaSpinner.getSelectedItem().toString();

                Boolean checkinsertdata = DB.insertCliente(DNI_Letra, Nombre, Empresa, Telefono, Comunidad, Provincia);
                if (checkinsertdata) {
                    Toast.makeText(AnadirCliente.this, "New Client Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AnadirCliente.this, ListViewActivityModify.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AnadirCliente.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(AnadirCliente.this, ListViewActivityModify.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("Range")
    private List<Provincia> llenarProvincias(String comunidad) {
        List<Provincia> listProvincias = new ArrayList<>();
        DBHelper DB = new DBHelper(AnadirCliente.this);
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
        DBHelper DB = new DBHelper(AnadirCliente.this);
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
