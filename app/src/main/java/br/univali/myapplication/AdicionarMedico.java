package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarMedico extends AppCompatActivity {

    EditText nome;
    EditText crm;
    EditText logradouro;
    EditText numero;
    EditText cidade;
    EditText uf;
    EditText celular;
    EditText fixo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_medico);

        nome = findViewById(R.id.edNome);
        crm = findViewById(R.id.edCrm);
        logradouro = findViewById(R.id.edLogradouro);
        numero = findViewById(R.id.edNumero);
        cidade = findViewById(R.id.edCidade);
        uf = findViewById(R.id.edUF);
        celular = findViewById(R.id.edCelular);
        fixo = findViewById(R.id.edFixo);
    }

    public void adicionarMedico(View v){

        String nomeString = nome.getText().toString();
        String crmString = crm.getText().toString();
        String logradouroString = logradouro.getText().toString();
        String numeroString = numero.getText().toString();
        String cidadeString = cidade.getText().toString();
        String ufString = uf.getText().toString();
        String celularString = celular.getText().toString();
        String fixoString = fixo.getText().toString();

        Toast.makeText(this, "Adicionado com Sucesso ", Toast.LENGTH_SHORT).show();
    }

}