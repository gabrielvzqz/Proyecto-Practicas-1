package com.example.empresaclientes.VentasAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.ProductosAdmin.Cliente;
import com.example.empresaclientes.ProductosAdmin.Item;
import com.example.empresaclientes.R;


import java.util.ArrayList;
import java.util.List;

public class ProductosVentasAdmin extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapterVentas adapter;
    private List<Item> itemList;
    private List<Item> itemListFull;
    private ImageButton imageButtonToggleColumns;
    private ImageButton imageListaDeLaCompra;
    private SearchView searchView;
    private boolean isSingleColumn = true; // Variable para controlar el estado de las columnas
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_ventas);

        recyclerView = findViewById(R.id.recyclerView);
        imageButtonToggleColumns = findViewById(R.id.imageButtonToggleColumns);
        imageListaDeLaCompra = findViewById(R.id.imageListaDeLaCompra);
        searchView = findViewById(R.id.searchView);
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

        String id = getIntent().getStringExtra("ID");
        String nombre = getIntent().getStringExtra("Nombre");
        String empresa = getIntent().getStringExtra("Empresa");
        String telefono = getIntent().getStringExtra("Telefono");
        String dniLetra = getIntent().getStringExtra("DNIyLetra");
        String comunidad = getIntent().getStringExtra("Comunidad");
        String provincia = getIntent().getStringExtra("Provincia");
        Cliente cliente = new Cliente(id, nombre, empresa, telefono, dniLetra, comunidad, provincia);
        listaClientes.add(cliente);
        DB = new DBHelper(this);

        // Recuperar productos de la base de datos
        itemList = DB.getAllProductos();
        itemListFull = DB.getAllProductos();

        adapter = new MyAdapterVentas(itemList, isSingleColumn, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);  // 1 columna por defecto
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        imageButtonToggleColumns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColumns();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // No necesitamos hacer nada aquí
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.setProductList(itemListFull); // Restaurar la lista original de productos
                } else {
                    adapter.getFilter().filter(newText); // Aplicar el filtro con el nuevo texto
                }
                return false;
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ProductosVentasAdmin.this, ListViewActivityVentasAdmin.class);
                startActivity(intent);
            }
        });
        imageListaDeLaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Item> selectedItems = adapter.getSelectedItems();
                // Redirige a la nueva actividad que deseas abrir
                Intent intent = new Intent(ProductosVentasAdmin.this, ListaDeLaCompraAdmin.class);
                intent.putExtra("Cliente", listaClientes.get(0));
                intent.putExtra("SelectedItems", new ArrayList<>(selectedItems));
                startActivity(intent);
            }
        });
    }


    private void toggleColumns() {
        if (isSingleColumn) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);  // 2 columnas
            recyclerView.setLayoutManager(layoutManager);
            isSingleColumn = false;
            imageButtonToggleColumns.setImageResource(R.drawable.lista);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1);  // 1 columna
            recyclerView.setLayoutManager(layoutManager);
            isSingleColumn = true;
            imageButtonToggleColumns.setImageResource(R.drawable.tabla);
        }
        recyclerView.setAdapter(adapter);
        adapter.toggleColumnCount(isSingleColumn);
    }


}