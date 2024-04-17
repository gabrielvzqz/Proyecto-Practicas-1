package com.example.empresaclientes.ProductosAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresaclientes.Menus.SeleccionImagenAdmin;
import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.R;

import java.util.List;

public class ProductosAdmin extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MyAdapter adapter;
    private List<Item> itemList;

    private List<Item> itemListFull;
    private ImageButton imageButtonToggleColumns;
    private SearchView searchView;
    private boolean isSingleColumn = true; // Variable para controlar el estado de las columnas
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        recyclerView = findViewById(R.id.recyclerView);
        imageButtonToggleColumns = findViewById(R.id.imageButtonToggleColumns);
        searchView = findViewById(R.id.searchView);

        DB = new DBHelper(this);

        // Recuperar producos de la base de datos
        itemList = DB.getAllProductos();
        itemListFull = DB.getAllProductos();

        adapter = new MyAdapter(itemList, isSingleColumn, this);

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
                Intent intent = new Intent(ProductosAdmin.this, SeleccionImagenAdmin.class);
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