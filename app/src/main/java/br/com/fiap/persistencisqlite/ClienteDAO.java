package br.com.fiap.persistencisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by logonrm on 27/04/2017.
 */

public class ClienteDAO extends SQLiteOpenHelper{

    private static final String DBNAME = "APP";
    private static final String TBCLIENTE = "cliente";
    public static final int DBVERSION = 1;

    public ClienteDAO(Context context){

        super(context, "DBNAME", null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TBCLIENTE + "( " +
                " `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " `nome` TEXT, " +
                " `email` TEXT " +
                ")";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put ("nome", cliente.getNome());
        values.put("email", cliente.getEmail());
        db.insert(TBCLIENTE, null, values);
        db.close();
    }
    public List<Cliente>all(){
        List<Cliente> clientes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TBCLIENTE, new String[]{"id","nome", "email"}, null,null,null,null, "nome ASC");

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String email = cursor.getString(2);
            clientes.add(new Cliente(id, nome, email));
        }
        db.close();
        return clientes;
    }
    public void delete (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBCLIENTE, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void update(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",cliente.getNome());
        cv.put("email",cliente.getEmail());
        db.update(TBCLIENTE, cv, "id = ?", new String[]{String.valueOf(cliente.getId())});
        db.close();
    }
}
