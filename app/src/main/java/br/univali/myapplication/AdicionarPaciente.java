package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarPaciente extends AppCompatActivity {


    EditText nome;
    EditText grp_sanguineo;
    EditText logradouro;
    EditText numero;
    EditText cidade;
    EditText uf;
    EditText celular;
    EditText fixo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_paciente);

        nome = findViewById(R.id.addNomePac);
        grp_sanguineo = findViewById(R.id.addGrpPac);
        logradouro = findViewById(R.id.addLogradouroPac);
        numero = findViewById(R.id.addnNumeroPac);
        cidade = findViewById(R.id.addCidadePac);
        uf = findViewById(R.id.addUfPac);
        celular = findViewById(R.id.addCelularPac);
        fixo = findViewById(R.id.addFixoPac);

    }

    public void adicionarPaciente(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("INSERT INTO paciente (nome, grp_sanguineo, logradouro, numero, cidade, uf, celular, fixo) VALUES ( ");
        sql_builder.append("'" + nome.getText().toString() + "', ");
        sql_builder.append(grp_sanguineo.getText().toString() + ", ");
        sql_builder.append("'" + logradouro.getText().toString() + "', ");
        sql_builder.append(numero.getText().toString() + ", ");
        sql_builder.append("'" + cidade.getText().toString() + "', ");
        sql_builder.append("'" + uf.getText().toString() + "', ");
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