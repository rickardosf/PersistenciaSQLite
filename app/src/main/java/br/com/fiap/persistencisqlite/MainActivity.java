package br.com.fiap.persistencisqlite;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ClienteDAO clienteDAO;
    ListView lstClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clienteDAO = new ClienteDAO(this);
        lstClientes = (ListView)findViewById(R.id.lstClientes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnCadastrar){
            Intent it = new Intent(this, CadastroAcivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
    public void atualizarLista(){
        List<Cliente> clientes = clienteDAO.all();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        lstClientes.setAdapter(adapter);
    }
}
