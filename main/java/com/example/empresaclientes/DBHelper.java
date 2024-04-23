package com.example.empresaclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.empresaclientes.ProductosAdmin.Item;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "EmpresaCliente.db"; // Cambio aquí el nombre de la base de datos
    private Context context;


    public DBHelper(Context context) {
        super(context, DBNAME, null, 2); // Uso DBNAME aquí
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists Clientes");
        MyDB.execSQL("drop Table if exists Comunidades");
        MyDB.execSQL("drop Table if exists Provincias");
        MyDB.execSQL("drop Table if exists Productos");
        MyDB.execSQL("drop Table if exists Carrito");

        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT not null)");

        MyDB.execSQL("create Table Comunidades(Nombre TEXT primary key)");

        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Andalucía')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Aragón')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Asturias')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Islas Baleares')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Canarias')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Cantabria')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Castilla y León')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Cataluña')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Comunidad Valenciana')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Extremadura')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Galicia')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Madrid')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Murcia')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Navarra')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('País Vasco')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('La Rioja')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Ceuta')");
        MyDB.execSQL("INSERT INTO Comunidades(Nombre) VALUES('Melilla')");

        MyDB.execSQL("create Table Carrito(ID Integer references Productos(ID), Nombre TEXT references Productos(Nombre), Precio Integer references Productos(Precio), Cantidad Integer references Productos(Cantidad), Foto TEXT references Productos(Foto))");

        MyDB.execSQL("create Table Productos(ID Integer primary key not null unique, Nombre TEXT, Precio Integer, Cantidad Integer, Foto TEXT)");

        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (1, 'Salmon', 34, 100, 'https://static.carrefour.es/hd_350x_/img_pim_food/283313_00_1.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (2, 'Dorada', 37, 100, 'https://www.pescaderiascorunesas.es/tienda-online/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/d/o/doradaedit.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (3, 'Langostinos', 40, 100, 'https://www.pescaderiaespe.com/wp-content/uploads/2022/03/Langostino-cocido.jpeg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (4, 'Camarones', 60, 100, 'https://www.pescadosaturnino.com/wp-content/uploads/2023/03/0049_camaron-cocido-1.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (5, 'Atún en lata', 15, 100, 'https://latastienda.com/wp-content/uploads/2022/06/ATUN-BLANCO-ALCACHOFA-DE-BRETANA.png');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (6, 'Sardinas en lata', 16, 100, 'https://disglobal.es/wp-content/uploads/2018/07/imagenes_4648398-621x577_464699ae.gif');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (7, 'Bacalao', 18, 100, 'https://pescadoacasa.com/wp-content/uploads/2018/03/bacalao-fresco-2kg-pescadoacasa.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (8, 'Calamares', 26, 100, 'https://www.peixacasa.cat/wp-content/uploads/2018/02/calamar-del-norte-peix-a-casa.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (9, 'Pulpo', 30, 100, 'https://p14.es/wp-content/uploads/2021/02/pulpo2.5kg.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (10, 'Trucha', 18, 100, 'https://www.pescadosaturnino.com/wp-content/uploads/2021/06/trucha-1.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (11, 'Anchoas en aceite', 30, 100, 'https://www.conservasnardin.com/105-large_default/anchoas-cantabrico-aceite-oliva-frasco-210g.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (12, 'Langosta', 70, 100, 'https://pescadoacasa.com/wp-content/uploads/2017/10/Marisco_de_Vigo-galeria-marisco-langosta-1466010168.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (13, 'Gambones', 10, 100, 'https://irun.market/wp-content/uploads/2023/10/Productos-15.jpeg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (14, 'Bogavante', 100, 100, 'https://mardamorosa.com/216-tm_large_default/bogavante-azul.jpg');");
        MyDB.execSQL("INSERT INTO Productos (ID, Nombre, Precio, Cantidad, Foto) VALUES (15, 'Mero', 57, 100, 'https://www.pescaderiascorunesas.es/tienda-online/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/e/mero.jpg');");

        MyDB.execSQL("create Table Provincias(Nombre TEXT primary key, comunidad TEXT references Comunidades(Nombre))");

        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Almería', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Cádiz', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Córdoba', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Granada', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Huelva', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Jaén', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Málaga', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Sevilla', 'Andalucía')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Huesca', 'Aragón')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Teruel', 'Aragón')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Zaragoza', 'Aragón')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Asturias', 'Asturias')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Ibiza', 'Islas Baleares')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Mallorca', 'Islas Baleares')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Menorca', 'Islas Baleares')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Las Palmas', 'Canarias')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Santa Cruz de Tenerife', 'Canarias')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Cantabria', 'Cantabria')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Ávila', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Burgos', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('León', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Palencia', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Salamanca', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Segovia', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Soria', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Valladolid', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Zamora', 'Castilla y León')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Albacete', 'Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Ciudad Real', 'Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Cuenca', 'Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Guadalajara', 'Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Toledo', 'Castilla-La Mancha')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Barcelona', 'Cataluña')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Girona', 'Cataluña')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Lleida', 'Cataluña')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Tarragona', 'Cataluña')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Alicante', 'Comunidad Valenciana')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Castellón', 'Comunidad Valenciana')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Valencia', 'Comunidad Valenciana')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Badajoz', 'Extremadura')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Cáceres', 'Extremadura')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('A Coruña', 'Galicia')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Lugo', 'Galicia')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Ourense', 'Galicia')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Pontevedra', 'Galicia')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Madrid', 'Madrid')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Murcia', 'Murcia')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Navarra', 'Navarra')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Álava', 'País Vasco')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Gipuzkoa', 'País Vasco')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Bizkaia', 'País Vasco')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('La Rioja', 'La Rioja')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Ceuta', 'Ceuta')");
        MyDB.execSQL("INSERT INTO Provincias(Nombre, Comunidad) VALUES('Melilla', 'Melilla')");

        MyDB.execSQL("create Table Clientes(ID Integer primary key not null unique, Nombre TEXT not null, Empresa TEXT not null, Telefono Integer not null unique, DNI_Letra TEXT not null unique, comunidad TEXT references Comunidades(Nombre), Provincia TEXT references Provincias(Nombre))");

        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(1, 'Juan Pérez', 'Restaurante A Costa', 669552883, '12345678Z', 'Madrid', 'Madrid')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(2, 'María López', 'Bar Maria', 699885774, '98765432W', 'Cataluña', 'Barcelona')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(3, 'Carlos García', 'Pescadería García', 663552441, '45678901T', 'Andalucia', 'Sevilla')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(4, 'Alvaro Cruz', 'Restaurante A Costa', 669589589, '78901234D', 'Valencia', 'Valencia')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(5, 'Sofia Lorenzo', 'Bar Maria', 666222444, '01234567X', 'Aragón', 'Zaragoza')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(6, 'Carlos Villa', 'Pescadería García', 684123123, '89012345R', 'Andalucia', 'Málaga')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(7, 'Pablo Hermida', 'Restaurante A Costa', 635715964, '23456789A', 'Valencia', 'Alicante')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(8, 'Raquel Gonzalez', 'Bar Maria', 698452159, '34567890G', 'Murcia', 'Murcia')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(9, 'Manuel Vazquez', 'Pescadería García', 630258147, '56789012M', 'Andalucia', 'Córdoba')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(10, 'Laura Sánchez', 'Restaurante A Costa', 612450987, '67890123Y', 'Castilla y León', 'Valladolid')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(11, 'Javier Muñoz', 'Bar Maria', 684153426, '89011223B', 'Navarra', 'Pamplona')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(12, 'Elena Torres', 'Pescadería García', 632145698, '90123456J', 'Pais Vasco', 'Bilbao')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(13, 'Antonio Ruiz', 'Restaurante A Costa', 692591843, '11223344S', 'Andalucia', 'Granada')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(14, 'Isabel Martin', 'Bar Maria', 687354219, '22334455H', 'Castilla y León', 'Salamanca')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(15, 'Francisco Soto', 'Pescadería García', 621549876, '33445566E', 'Castilla La Mancha', 'Toledo')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(16, 'Carmen Navarro', 'Restaurante A Costa', 624785312, '44556677Q', 'Castilla y León', 'Burgos')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(17, 'Luisa Ramírez', 'Bar Maria', 654394284, '55667788V', 'Andalucia', 'Sevilla')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(18, 'Miguel Ángel Díaz', 'Pescadería García', 684351684, '66778899L', 'Madrid', 'Madrid')");
        MyDB.execSQL("INSERT INTO Clientes(ID, Nombre, Empresa, Telefono, DNI_Letra, Comunidad, Provincia) VALUES(19, 'Manuel Antonio Suárez', 'Pescadería García', 684351123, '77889900P', 'Cataluña', 'Barcelona')");
    }

    // Método para recuperar todos los registros de la tabla Clientes
    public ArrayList<String> getAllClientes() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes", null);

        while (cursor.moveToNext()) {
            String id = String.valueOf(cursor.getInt(0));
            String nombre = cursor.getString(1);
            String empresa = cursor.getString(2);
            String telefono = String.valueOf(cursor.getInt(3));
            String dniLetra = cursor.getString(4);
            String comunidad = cursor.getString(5);
            String provincia = cursor.getString(6);

            arrayList.add(id + ". " + nombre + " - " + empresa + " - " + telefono + " - " + dniLetra + " - " + comunidad + " - " + provincia);
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
        MyDB.execSQL("drop Table if exists Provincias");
        MyDB.execSQL("drop Table if exists Comunidades");
        MyDB.execSQL("drop Table if exists Productos");
        MyDB.execSQL("drop Table if exists Carrito");

        onCreate(MyDB);
    }

    public boolean insertCliente(String name, String empresa, String telefono, String dniLetra, String comunidad, String provincia){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", name);
        contentValues.put("Empresa", empresa);
        contentValues.put("Telefono", telefono);
        contentValues.put("DNI_Letra", dniLetra);
        contentValues.put("Comunidad", comunidad);
        contentValues.put("Provincia", provincia);

        long result = MyDB.insert("Clientes", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertarProductoEnCarrito(String nombre, String precio, String cantidad, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", nombre);
        contentValues.put("Precio", precio);
        contentValues.put("Cantidad", cantidad);
        contentValues.put("Foto", foto);

        long resultado = db.insert("Carrito", null, contentValues);
        db.close();

        return resultado != -1;
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
    private int getResourceIdByName(String resourceName) {
        Context context = this.context;
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
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
    public Boolean deletedata (String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes where ID=?", new String[]{id});
        if (cursor.getCount() > 0){
            long result = MyDB.delete("Clientes", "ID=?", new String[]{id});
            if (result > 0) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public Boolean updatedata(Integer id, String nombre, String empresa, Integer telefono, String dniLetra, String comunidad, String provincia) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", nombre);
        contentValues.put("Empresa", empresa);
        contentValues.put("Telefono", telefono);
        contentValues.put("DNI_Letra", dniLetra);
        contentValues.put("Comunidad", comunidad);
        contentValues.put("Provincia", provincia);

        Cursor cursor = MyDB.rawQuery("SELECT * FROM Clientes where ID=?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0) {
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

    public Cursor mostrarComunidades() {
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            return MyDB.rawQuery("SELECT Nombre FROM Comunidades", null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Cursor mostrarProvincias(String comunidad) {
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            return MyDB.rawQuery("SELECT Nombre FROM Provincias WHERE comunidad = ?", new String[]{comunidad});
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Item> getAllProductos() {
        List<Item> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM Productos";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int precio = cursor.getInt(2);
                String cantidad = cursor.getString(3);
                // Asumiendo que la columna "Foto" es una cadena con el nombre del recurso drawable
                String foto = cursor.getString(4);

                // Crear un nuevo Item y añadirlo a la lista
                Item item = new Item(getResourceIdByName(foto), nombre, "Price: " + precio + "€", cantidad, foto, false);
                productList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }


}
