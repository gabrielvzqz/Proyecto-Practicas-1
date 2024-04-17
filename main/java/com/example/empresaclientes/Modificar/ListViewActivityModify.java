package com.example.empresaclientes.Modificar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.Menus.SeleccionImagenAdmin;
import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.R;

import java.util.ArrayList;

public class ListViewActivityModify extends AppCompatActivity {

    ListView listView;
    DBHelper DB;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombresClientesList;  // Lista original de datos

    ArrayList<String> ClientesList;  // Lista original de datos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_modify);

        listView = findViewById(R.id.listViewAdmin);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        DB = new DBHelper(this);

        nombresClientesList = DB.getAllClientesNames();
        ClientesList = DB.getAllClientes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresClientesList);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.getFilter().filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        // Agregar el OnItemClickListener al ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el ítem de la lista original
                String clienteSeleccionado = ClientesList.get(position);

                // Imprimir el valor de clienteSeleccionado para depuración
                Log.d("ClienteSeleccionado", clienteSeleccionado);

                String[] datos = clienteSeleccionado.split("-");

                // Verificar el tamaño del array datos
                if (datos.length == 6) {
                    // Abrir la actividad DetailActivity con los datos del cliente seleccionado
                    Intent intent = new Intent(ListViewActivityModify.this, ModifyDetailActivity.class);
                    intent.putExtra("ID", datos[0].split("\\. ")[0]);
                    intent.putExtra("Nombre", datos[0].split("\\. ")[1]);
                    intent.putExtra("Empresa", datos[1]);
                    intent.putExtra("Telefono", datos[2]);
                    intent.putExtra("DNI_Letra", datos[3]);
                    intent.putExtra("Comunidad", datos[4]);
                    intent.putExtra("Provincia", datos[5]);
                    startActivity(intent);
                } else {
                    // Mostrar un mensaje de error o hacer algo en caso de que los datos no sean los esperados
                    // Por ejemplo:
                    Toast.makeText(ListViewActivityModify.this, "Error al obtener los datos del cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });




        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad al hacer clic en el botón
                Intent intent = new Intent(ListViewActivityModify.this, AnadirCliente.class);
                startActivity(intent);
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ListViewActivityModify.this, SeleccionImagenAdmin.class);
                startActivity(intent);
            }
        });
    }
}
