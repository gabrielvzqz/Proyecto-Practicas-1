package com.example.empresaclientes.VentasAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.Menus.SeleccionImagenAdmin;
import com.example.empresaclientes.ProductosAdmin.Cliente;
import com.example.empresaclientes.ProductosAdmin.Item;
import com.example.empresaclientes.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ListaDeLaCompraAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_la_compra_admin);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        List<Item> selectedItems = (List<Item>) getIntent().getSerializableExtra("SelectedItems");

        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewEmpresa = findViewById(R.id.textViewEmpresa);
        ListView listViewProductos = findViewById(R.id.listViewProductos);
        TextView textViewTotal = findViewById(R.id.textViewTotal);


        String nombre = cliente.getNombre();
        String empresa = cliente.getEmpresa();


        final double[][] total = {{0}};
        // Crear un ArrayAdapter personalizado para el ListView
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, R.layout.item_lista_de_la_compra, R.id.textViewNombreProducto, selectedItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textViewNombreProducto = view.findViewById(R.id.textViewNombreProducto);
                TextView textViewPrecio = view.findViewById(R.id.textViewPrecio);
                ImageView imageViewProducto = view.findViewById(R.id.imageViewProducto);
                TextView textViewCantidad = view.findViewById(R.id.textViewCantidad);
                ImageView imgRestar = view.findViewById(R.id.imgRestar);
                ImageView imgSumar = view.findViewById(R.id.imgSumar);

                Item item = getItem(position);

                if (item != null) {
                    textViewNombreProducto.setText(item.getNombre());
                    textViewPrecio.setText(item.getPrecio());

                    final int[][] cantidad = {{1}};  // Cantidad inicial
                    textViewCantidad.setText(String.valueOf(cantidad[0][0]));
                    // Cargar la imagen usando Glide (asegúrate de agregar la dependencia de Glide en tu build.gradle)
                    Glide.with(ListaDeLaCompraAdmin.this)
                            .load(item.getFoto())
                            .placeholder(R.drawable.carrito) // Imagen por defecto
                            .error(R.drawable.carrito)       // Imagen en caso de error
                            .into(imageViewProducto);

                    imgSumar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Sumar al total
                            try {
                                String precioStr = item.getPrecio().replace("Price: ", "").replace("€", "").trim();
                                double precio = Double.parseDouble(precioStr);
                                total[0][0] += precio;
                                textViewTotal.setText("Total: " + total[0][0] + "€");
                                cantidad[0][0]++;
                                textViewCantidad.setText(String.valueOf(cantidad[0][0]));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    imgRestar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Restar al total
                            try {
                                String precioStr = item.getPrecio().replace("Price: ", "").replace("€", "").trim();
                                double precio = Double.parseDouble(precioStr);
                                if (total[0][0] >= precio && cantidad[0][0] > 1) {
                                    total[0][0] -= precio;
                                    textViewTotal.setText("Total: " + total[0][0] + "€");
                                    cantidad[0][0]--;
                                    textViewCantidad.setText(String.valueOf(cantidad[0][0]));
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                return view;
            }
        };

        listViewProductos.setAdapter(adapter);

        for (Item item : selectedItems) {
            try {
                // Suponiendo que el precio está en un formato que se puede convertir a double, por ejemplo, "200€"
                String precioStr = item.getPrecio().replace("Price: ", "").replace("€", "").trim();
                double precio = Double.parseDouble(precioStr);
                total[0][0] += precio;
            } catch (NumberFormatException e) {
                // Manejar cualquier error al convertir el precio a double
                e.printStackTrace();
            }
        }

        textViewTotal.setText("Total: " + total[0][0] + "€");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad ListViewActivityAdmin
                Intent intent = new Intent(ListaDeLaCompraAdmin.this, ListViewActivityVentasAdmin.class);
                startActivity(intent);
            }
        });

        Button btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);
        btnFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListaDeLaCompraAdmin.this, "Purchased succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaDeLaCompraAdmin.this, SeleccionImagenAdmin.class);
                startActivity(intent);
            }
        });
    }
}