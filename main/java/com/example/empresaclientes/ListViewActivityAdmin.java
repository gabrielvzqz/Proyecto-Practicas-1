package com.example.empresaclientes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewActivityAdmin extends AppCompatActivity {

    ListView listView;
    DBHelper DB;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> clientesList;  // Lista original de datos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_admin);

        listView = findViewById(R.id.listViewAdmin);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        DB = new DBHelper(this);

        clientesList = DB.getAllClientes();  // Obtener la lista original de datos
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientesList);
        listView.setAdapter(adapter);

        // Configurar el OnQueryTextListener para el SearchView
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
                String clienteSeleccionado = clientesList.get(position);
                String[] datos = clienteSeleccionado.split(" - ");

                // Abrir la actividad DetailActivity con los datos del cliente seleccionado
                Intent intent = new Intent(ListViewActivityAdmin.this, DetailActivityAdmin.class);
                intent.putExtra("ID", datos[0].split("\\. ")[0]);
                intent.putExtra("Nombre", datos[0].split("\\. ")[1]);
                intent.putExtra("Empresa", datos[1]);
                intent.putExtra("Telefono", datos[2]);
                startActivity(intent);
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad al hacer clic en el botón
                Intent intent = new Intent(ListViewActivityAdmin.this, AnadirCliente.class);
                startActivity(intent);
            }
        });
    }
}
