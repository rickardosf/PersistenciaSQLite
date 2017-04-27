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
    }
    public List<Cliente>all(){
        List<Cliente> clientes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TBCLIENTE, new String[]{"nome", "email"}, null,null,null,null, "nome ASC");

        while (cursor.moveToNext()){
            String nome = cursor.getString(0);
            String email = cursor.getString(1);
            clientes.add(new Cliente(nome, email));
        }
        return clientes;
    }
}
