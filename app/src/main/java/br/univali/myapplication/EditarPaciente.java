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

public class EditarPaciente extends AppCompatActivity {

    String _id;
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
        setContentView(R.layout.activity_editar_paciente);

        nome = findViewById(R.id.editNomePac);
        grp_sanguineo = findViewById(R.id.editGrpPac);
        logradouro = findViewById(R.id.editLogradouroPac);
        numero = findViewById(R.id.editNumeroPac);
        cidade = findViewById(R.id.editCidadePac);
        uf = findViewById(R.id.editUfPac);
        celular = findViewById(R.id.editCelularPac);
        fixo = findViewById(R.id.editFixoPac);

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



        Intent valores =  getIntent();
        _id = valores.getStringExtra("_id");
        nome.setText(valores.getStringExtra("nome"));

        int selecaoGrp = Integer.parseInt(valores.getStringExtra("grp_sanguineo"));
        grp_sanguineo.setSelection(selecaoGrp);

        logradouro.setText(valores.getStringExtra("logradouro"));
        numero.setText(valores.getStringExtra("numero"));
        cidade.setText(valores.getStringExtra("cidade"));
        String selecaoUf = valores.getStringExtra("uf");
        int aux = 0;
        for (String c : UF ){
            if(c.equals(selecaoUf)){
                break;
            }
            aux++;
        }
        uf.setSelection(aux);
        celular.setText(valores.getStringExtra("celular"));
        fixo.setText(valores.getStringExtra("fixo"));

    }

    public void editarPaciente(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("UPDATE paciente SET ");
        sql_builder.append("nome = '" + nome.getText().toString() + "', ");
        sql_builder.append("grp_sanguineo = '"+ numeroGrp + "', ");
        sql_builder.append("logradouro = '" + logradouro.getText().toString() + "', ");
        sql_builder.append("numero = " + numero.getText().toString() + ", ");
        sql_builder.append("cidade = '" + cidade.getText().toString() + "', ");
        sql_builder.append("uf = '" + stringUf + "', ");
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