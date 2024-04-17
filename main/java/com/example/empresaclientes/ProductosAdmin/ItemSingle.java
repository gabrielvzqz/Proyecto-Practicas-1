package com.example.empresaclientes.ProductosAdmin;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.DBHelper;
import com.example.empresaclientes.R;

public class ItemSingle extends AppCompatActivity {

    TextView NombreProducto, PrecioProducto, CantidadProducto;
    CheckBox checkBox;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_layout_single_ventas);
        NombreProducto = findViewById(R.id.NombreProducto);
        PrecioProducto = findViewById(R.id.PrecioProducto);
        CantidadProducto = findViewById(R.id.CantidadProducto);
        checkBox = findViewById(R.id.checkBox);

        DB = new DBHelper(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String Nombre = NombreProducto.getText().toString();
                    String Precio = PrecioProducto.getText().toString();
                    String Cantidad = CantidadProducto.getText().toString();
                }
            }
        });
    }
}