package com.example.empresaclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "EmpresaCliente.db"; // Cambio aquí el nombre de la base de datos

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1); // Uso DBNAME aquí
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("drop Table if exists Clientes");
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT not null)");
        MyDB.execSQL("create Table Clientes(ID Integer primary key not null unique, Nombre TEXT not null, Empresa TEXT not null, Telefono Integer not null unique)");

        // Insertar datos en la tabla Clientes
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(1, 'Juan Pérez', 'Restaurante A Costa', 669552883)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(2, 'María López', 'Bar Maria', 699885774)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(3, 'Carlos García', 'Pescadería García', 663552441)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(4, 'Alvaro Cruz', 'Restaurante A Costa', 669589589)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(5, 'Sofia Lorenzo', 'Bar Maria', 666222444)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(6, 'Carlos Villa', 'Pescadería García', 684123123)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(7, 'Pablo Hermida', 'Restaurante A Costa', 635715964)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(8, 'Raquel Gonzalez', 'Bar Maria', 698452159)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(9, 'Manuel Vazquez', 'Pescadería García', 630258147)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(10, 'Laura Sánchez', 'Restaurante A Costa', 612450987)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(11, 'Javier Muñoz', 'Bar Maria', 684153426)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(12, 'Elena Torres', 'Pescadería García', 632145698)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(13, 'Antonio Ruiz', 'Restaurante A Costa', 692591843)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(14, 'Isabel Martin', 'Bar Maria', 687354219)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(15, 'Francisco Soto', 'Pescadería García', 621549876)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(16, 'Carmen Navarro', 'Restaurante A Costa', 624785312)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(17, 'Luisa Ramírez', 'Bar Maria', 654394284)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(18, 'Miguel Ángel Díaz', 'Pescadería García', 684351684)");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono) VALUES(19, 'Manuel Antonio Suárez', 'Pescadería García', 684351123)");
        // ... (los demás insert)

    }

    // Método para recuperar todos los registros de la tabla Clientes
    public ArrayList<String> getAllClientes() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase(); // Cambio aquí a getReadableDatabase()
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes", null);

        while (cursor.moveToNext()) {
            String id = String.valueOf(cursor.getInt(0));
            String nombre = cursor.getString(1);
            String empresa = cursor.getString(2);
            String telefono = String.valueOf(cursor.getInt(3));
            arrayList.add(id + ". " + nombre + " - " + empresa + " - " + telefono);
        }

        cursor.close();
        MyDB.close();
        return arrayList;
    }
    public ArrayList<String> getAllClientesNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT Nombre FROM Clientes", null);

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(0);
            arrayList.add(nombre);
        }

        cursor.close();
        MyDB.close();
        return arrayList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists Clientes");
        onCreate(MyDB);
    }

    public boolean insertCliente(String name, String empresa, String telefono){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", name);
        contentValues.put("Empresa", empresa);
        contentValues.put("Telefono", telefono);
        long result = MyDB.insert("Clientes", null, contentValues);
        if (result == -1) return false;
        else return true;

    }
    public boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    public Boolean deletedata (String nombre){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes where Nombre=?", new String[]{nombre});
        if (cursor.getCount() > 0){
            long result = MyDB.delete("Clientes", "Nombre=?", new String[]{nombre});
            if (result > 0) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public Boolean updatedata(Integer id, String nombre, String empresa, Integer telefono){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Nombre", nombre);
        contentValues.put("Empresa", empresa);
        contentValues.put("Telefono", telefono);

        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes where ID=?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0){
            long result = MyDB.update("Clientes", contentValues, "ID=?", new String[]{String.valueOf(id)});
            cursor.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            cursor.close();
            return false;
        }
    }

}
