package com.example.empresaclientes.VentasAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.Menus.SeleccionImagenAdmin;
import com.example.empresaclientes.R;

import java.util.ArrayList;

public class ListViewActivityVentasAdmin extends AppCompatActivity {

    ListView listView;
    DBHelper DB;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombresClientesList;  // Lista original de datos

    ArrayList<String> ClientesList;  // Lista original de datos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_ventas_admin);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        DB = new DBHelper(this);

        nombresClientesList = DB.getAllClientesNames();
        ClientesList = DB.getAllClientes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresClientesList);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el ítem de la lista original
                String clienteSeleccionado = ClientesList.get(position);
                String[] datos = clienteSeleccionado.split(" - ");

                // Abrir la actividad DetailActivity con los datos del cliente seleccionado
                Intent intent = new Intent(ListViewActivityVentasAdmin.this, ProductosVentasAdmin.class);
                intent.putExtra("ID", datos[0].split("\\. ")[0]);
                intent.putExtra("Nombre", datos[0].split("\\. ")[1]);
                intent.putExtra("Empresa", datos[1]);
                intent.putExtra("Telefono", datos[2]);
                intent.putExtra("DNI_Letra", datos[3]);
                intent.putExtra("Comunidad", datos[4]);
                intent.putExtra("Provincia", datos[5]);
                startActivity(intent);
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ListViewActivityVentasAdmin.this, SeleccionImagenAdmin.class);
                startActivity(intent);
            }
        });
    }
}