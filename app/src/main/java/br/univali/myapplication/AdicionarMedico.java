package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarMedico extends AppCompatActivity {

    EditText nome;
    EditText crm;
    EditText logradouro;
    EditText numero;
    EditText cidade;
    Spinner uf;
    String stringUf;
    EditText celular;
    EditText fixo;
    SQLiteDatabase db;

    final String[] UF = new String[] {
            "RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN",
            "PB", "PE", "AL", "SE", "BA", "MG", "ES", "RJ", "SP", "PR", "SC",
            "RS", "MS", "MT", "GO", "DF"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_medico);

        nome = findViewById(R.id.addNomeMed);
        crm = findViewById(R.id.addCrmMed);
        logradouro = findViewById(R.id.addLogradouroMed);
        numero = findViewById(R.id.addNumeroMed);
        cidade = findViewById(R.id.addCidadeMed);
        uf = findViewById(R.id.addUFMed);
        celular = findViewById(R.id.addCelularMed);
        fixo = findViewById(R.id.addFixoMed);

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        ArrayAdapter<String> spUfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UF);
        uf.setAdapter(spUfAdapter);

        uf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringUf = UF[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void adicionarMedico(View v){


        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("INSERT INTO medico (nome, crm, logradouro, numero, cidade, uf, celular, fixo) VALUES ( ");
        sql_builder.append("'" + nome.getText().toString() + "', ");
        sql_builder.append("'" + crm.getText().toString() + "', ");
        sql_builder.append("'" + logradouro.getText().toString() + "', ");
        sql_builder.append(numero.getText().toString() + ", ");
        sql_builder.append("'" + cidade.getText().toString() + "', ");
        sql_builder.append("'" + stringUf + "', ");
        sql_builder.append("'" + celular.getText().toString() + "', ");
        sql_builder.append("'" + fixo.getText().toString() + "');");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Adicionado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

        db.close();
    }
}