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
        Intent i = new Intent(MainActivity.this, ListarPaciente.class);
        startActivity(i);
    }

    public void abreListarConsulta(View v){
        Intent i = new Intent(MainActivity.this, ListarConsulta.class);
        startActivity(i);
    }

    private void criarBancoDados(){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_1 =  new StringBuilder();
        StringBuilder sql_2 =  new StringBuilder();
        StringBuilder sql_3 =  new StringBuilder();

        sql_1.append("CREATE TABLE IF NOT EXISTS medico (");
        sql_1.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_1.append("nome VARCHAR(50), ");
        sql_1.append("crm VARCHAR(20), ");
        sql_1.append("logradouro VARCHAR(100), ");
        sql_1.append("numero MEDIUMINT(8), ");
        sql_1.append("cidade VARCHAR(30), ");
        sql_1.append("uf VARCHAR(2), ");
        sql_1.append("celular VARCHAR(20), ");
        sql_1.append("fixo VARCHAR(20)");
        sql_1.append(");");

        try {
            db.execSQL(sql_1.toString());
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        sql_2.append("CREATE TABLE IF NOT EXISTS paciente (");
        sql_2.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_2.append("nome VARCHAR(50), ");
        sql_2.append("grp_sanguineo TINYINT(1), ");
        sql_2.append("logradouro VARCHAR(100), ");
        sql_2.append("numero MEDIUMINT(8), ");
        sql_2.append("cidade VARCHAR(30), ");
        sql_2.append("uf VARCHAR(2), ");
        sql_2.append("celular VARCHAR(20), ");
        sql_2.append("fixo VARCHAR(20)");
        sql_2.append(");");

        try {
            db.execSQL(sql_2.toString());
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        sql_3 .append("CREATE TABLE IF NOT EXISTS consulta (");
        sql_3 .append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql_3 .append("paciente_id INTEGER NOT NULL, ");
        sql_3 .append("medico_id INTEGER NOT NULL, ");
        sql_3 .append("data_hora_inicio DATETIME, ");
        sql_3 .append("data_hora_fim DATETIME, ");
        sql_3 .append("observacao VARCHAR(200), ");
        sql_3 .append("FOREIGN KEY(paciente_id) REFERENCES paciente(id), ");
        sql_3 .append("FOREIGN KEY(medico_id) REFERENCES medico(id)");
        sql_3 .append(");");

        try {
            db.execSQL(sql_3.toString());
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        db.close();

    }

}