package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarBancoDados();
    }

    public void abreAddMedico(View v){
        Intent i = new Intent(MainActivity.this, AdicionarMedico.class);
        startActivity(i);
    }

    public void abreAddPaciente(View v){
        Intent i = new Intent(MainActivity.this, AdicionarPaciente.class);
        startActivity(i);
    }

    public void abreAddConsulta(View v){
        Intent i = new Intent(MainActivity.this, AdicionarConsulta.class);
        startActivity(i);
    }

    public void abreListarMedico(View v){
        Intent i = new Intent(MainActivity.this, ListarMedico.class);
        startActivity(i);
    }

    public void abreListarPaciente(View v){

    }

    public void abreListarConsulta(View v){

    }

    private void criarBancoDados(){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_banco =  new StringBuilder();

        sql_banco.append("CREATE TABLE IF NOT EXISTS medico (");
        sql_banco.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_banco.append("nome VARCHAR(50), ");
        sql_banco.append("crm VARCHAR(20), ");
        sql_banco.append("logradouro VARCHAR(100), ");
        sql_banco.append("numero MEDIUMINT(8), ");
        sql_banco.append("cidade VARCHAR(30), ");
        sql_banco.append("uf VARCHAR(2), ");
        sql_banco.append("celular VARCHAR(20), ");
        sql_banco.append("fixo VARCHAR(20)");
        sql_banco.append(");");

        sql_banco.append("CREATE TABLE IF NOT EXISTS paciente (");
        sql_banco.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_banco.append("nome VARCHAR(50), ");
        sql_banco.append("grp_sanguineo TINYINT(1), ");
        sql_banco.append("logradouro VARCHAR(100), ");
        sql_banco.append("numero MEDIUMINT(8), ");
        sql_banco.append("cidade VARCHAR(30), ");
        sql_banco.append("uf VARCHAR(2), ");
        sql_banco.append("celular VARCHAR(20), ");
        sql_banco.append("fixo VARCHAR(20)");
        sql_banco.append(");");

        sql_banco.append("CREATE TABLE IF NOT EXISTS consulta (");
        sql_banco.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_banco.append("paciente_id INTEGER NOT NULL, ");
        sql_banco.append("medico_id INTEGER NOT NULL, ");
        sql_banco.append("data_hora_inicio DATETIME, ");
        sql_banco.append("data_hora_fim DATETIME, ");
        sql_banco.append("observacao VARCHAR(200), ");
        sql_banco.append("FOREIGN KEY(paciente_id) REFERENCES paciente(id), ");
        sql_banco.append("FOREIGN KEY(medico_id) REFERENCES medico(id)");
        sql_banco.append(");");

        try {
            db.execSQL(sql_banco.toString());
            Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        db.close();

    }

}