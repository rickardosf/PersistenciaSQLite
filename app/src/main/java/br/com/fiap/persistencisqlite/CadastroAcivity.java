package br.com.fiap.persistencisqlite;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroAcivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    Cliente cliente;
    ClienteDAO clienteDAO;
    boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);

        clienteDAO = new ClienteDAO(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null ) {
            isUpdate = true;
            cliente = (Cliente) getIntent().getExtras().get("cliente");
            if (cliente != null) {
                getSupportActionBar().setTitle(R.string.editar_cliente);
                edtNome.setText(cliente.getNome());
                edtEmail.setText(cliente.getEmail());
            }
        }
    }

    public void salvar(View view) {
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        if (isUpdate) {
            cliente.setNome(nome);
            cliente.setEmail(email);
            clienteDAO.update(cliente);
        }else{
            cliente = new Cliente(nome, email);
            clienteDAO.insert(cliente);
        }
        finish();
    }
}
