package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarPaciente extends AppCompatActivity {

    String _id;
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
        setContentView(R.layout.activity_editar_paciente);

        nome = findViewById(R.id.editNomePac);
        grp_sanguineo = findViewById(R.id.editGrpPac);
        logradouro = findViewById(R.id.editLogradouroPac);
        numero = findViewById(R.id.editNumeroPac);
        cidade = findViewById(R.id.editCidadePac);
        uf = findViewById(R.id.editUfPac);
        celular = findViewById(R.id.editCelularPac);
        fixo = findViewById(R.id.editFixoPac);

        Intent valores =  getIntent();
        _id = valores.getStringExtra("_id");
        nome.setText(valores.getStringExtra("nome"));
        grp_sanguineo.setText(valores.getStringExtra("grp_sanguineo"));
        logradouro.setText(valores.getStringExtra("logradouro"));
        numero.setText(valores.getStringExtra("numero"));
        cidade.setText(valores.getStringExtra("cidade"));
        uf.setText(valores.getStringExtra("uf"));
        celular.setText(valores.getStringExtra("celular"));
        fixo.setText(valores.getStringExtra("fixo"));

    }

    public void editarPaciente(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("UPDATE paciente SET ");
        sql_builder.append("nome = '" + nome.getText().toString() + "', ");
        sql_builder.append("grp_sanguineo = '"+ grp_sanguineo.getText().toString() + "', ");
        sql_builder.append("logradouro = '" + logradouro.getText().toString() + "', ");
        sql_builder.append("numero = " + numero.getText().toString() + ", ");
        sql_builder.append("cidade = '" + cidade.getText().toString() + "', ");
        sql_builder.append("uf = '" + uf.getText().toString() + "', ");
        sql_builder.append("celular = '" + celular.getText().toString() + "', ");
        sql_builder.append("fixo = '" + fixo.getText().toString() + "'");
        sql_builder.append("WHERE _id = " + _id + ";");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Editado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), ListarPaciente.class);
        startActivity(i);

        db.close();
    }

    public void excluirPaciente(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("DELETE FROM paciente ");
        sql_builder.append("WHERE _id = " + _id + ";");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Editado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), ListarPaciente.class);
        startActivity(i);

        db.close();
    }


}