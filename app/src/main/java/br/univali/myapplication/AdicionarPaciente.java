package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarPaciente extends AppCompatActivity {

    EditText nome;
    Spinner grp_sanguineo;
    int numeroGrp;
    EditText logradouro;
    EditText numero;
    EditText cidade;
    Spinner uf;
    String stringUf;
    EditText celular;
    EditText fixo;
    SQLiteDatabase db;

    final String[] GRP = new String[] {
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    };

    final String[] UF = new String[] {
            "RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN",
            "PB", "PE", "AL", "SE", "BA", "MG", "ES", "RJ", "SP", "PR", "SC",
            "RS", "MS", "MT", "GO", "DF"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_paciente);

        nome = findViewById(R.id.addNomePac);
        grp_sanguineo = findViewById(R.id.addGrpPac);
        logradouro = findViewById(R.id.addLogradouroPac);
        numero = findViewById(R.id.addNumeroPac);
        cidade = findViewById(R.id.addCidadePac);
        uf = findViewById(R.id.addUfPac);
        celular = findViewById(R.id.addCelularPac);
        fixo = findViewById(R.id.addFixoPac);

        ArrayAdapter<String> spGrpAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GRP);
        grp_sanguineo.setAdapter(spGrpAdapter);

        grp_sanguineo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numeroGrp = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                numeroGrp = 10;
            }
        });

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

    public void adicionarPaciente(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("INSERT INTO paciente (nome, grp_sanguineo, logradouro, numero, cidade, uf, celular, fixo) VALUES ( ");
        sql_builder.append("'" + nome.getText().toString() + "', ");
        sql_builder.append(numeroGrp + ", ");
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