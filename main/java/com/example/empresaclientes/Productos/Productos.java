package com.example.empresaclientes.Productos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresaclientes.R;

import java.util.ArrayList;
import java.util.List;

public class Productos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;
    private ImageButton imageButtonToggleColumns;
    private boolean isSingleColumn = true; // Variable para controlar el estado de las columnas

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        recyclerView = findViewById(R.id.recyclerView);
        imageButtonToggleColumns = findViewById(R.id.imageButtonToggleColumns);

        itemList = new ArrayList<>();
        // Añade elementos a la lista (por ejemplo, imágenes y textos)
        itemList.add(new Item(R.drawable.clientescolor, "Title 1", "Description 1"));
        itemList.add(new Item(R.drawable.clientescolor, "Title 2", "Description 2"));
        itemList.add(new Item(R.drawable.clientescolor, "Title 3", "Description 3"));
        itemList.add(new Item(R.drawable.clientescolor, "Title 4", "Description 4"));

        adapter = new MyAdapter(itemList, isSingleColumn);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);  // 1 columna por defecto
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        imageButtonToggleColumns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColumns();
            }
        });
    }

    private void toggleColumns() {
        if (isSingleColumn) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);  // 2 columnas
            recyclerView.setLayoutManager(layoutManager);
            isSingleColumn = false;
            imageButtonToggleColumns.setImageResource(R.drawable.tabla);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1);  // 1 columna
            recyclerView.setLayoutManager(layoutManager);
            isSingleColumn = true;
            imageButtonToggleColumns.setImageResource(R.drawable.tabla);
        }
        adapter.toggleColumnCount(isSingleColumn);
    }
}