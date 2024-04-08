package com.example.empresaclientes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewActivityUser extends AppCompatActivity {

    ListView listView;
    DBHelper DB;
    SearchView searchView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_user);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        DB = new DBHelper(this);

        ArrayList<String> clientesList = DB.getAllClientes();
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
                String clienteSeleccionado = (String) parent.getItemAtPosition(position);
                String[] datos = clienteSeleccionado.split(" - ");

                // Abrir la actividad DetailActivity con los datos del cliente seleccionado
                Intent intent = new Intent(ListViewActivityUser.this, DetailActivityUser.class);
                intent.putExtra("ID", datos[0].split("\\. ")[0]);
                intent.putExtra("Nombre", datos[0].split("\\. ")[1]);
                intent.putExtra("Empresa", datos[1]);
                intent.putExtra("Telefono", datos[2]);
                startActivity(intent);
            }
        });
    }
}