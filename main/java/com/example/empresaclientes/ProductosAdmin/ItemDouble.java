package com.example.empresaclientes.ProductosAdmin;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empresaclientes.R;

public class ItemDouble extends AppCompatActivity {
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_layout_double_ventas);

        checkBox = findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {
                    // Acción cuando el CheckBox no está seleccionado
                    // Por ejemplo, puedes ocultar un elemento o realizar alguna operación
                    // Ejemplo: Toast.makeText(TuActividad.this, "CheckBox no seleccionado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}