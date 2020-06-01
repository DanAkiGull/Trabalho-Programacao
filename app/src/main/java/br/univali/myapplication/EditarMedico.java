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

public class EditarMedico extends AppCompatActivity {

    String _id;
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
        setContentView(R.layout.activity_editar_medico);

        nome = findViewById(R.id.editNomeMed);
        crm = findViewById(R.id.editCrmMed);
        logradouro = findViewById(R.id.editLogradouroMed);
        numero = findViewById(R.id.editNumeroMed);
        cidade = findViewById(R.id.editCidadeMed);
        uf = findViewById(R.id.editUFMed);
        celular = findViewById(R.id.editCelularMed);
        fixo = findViewById(R.id.editFixoMed);

        ArrayAdapter<String> spUfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UF);
        uf.setAdapter(spUfAdapter);

        uf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringUf = UF[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stringUf = "";
            }
        });

        Intent valores =  getIntent();
        _id = valores.getStringExtra("_id");
        nome.setText(valores.getStringExtra("nome"));
        crm.setText(valores.getStringExtra("crm"));
        logradouro.setText(valores.getStringExtra("logradouro"));
        numero.setText(valores.getStringExtra("numero"));
        cidade.setText(valores.getStringExtra("cidade"));
        String selecao = valores.getStringExtra("uf");
        int aux = 0;
        for (String c : UF ){
            if(c.equals(selecao)){
                break;
            }
            aux++;
        }
        uf.setSelection(aux);
        celular.setText(valores.getStringExtra("celular"));
        fixo.setText(valores.getStringExtra("fixo"));

    }

    public void editarMedico(View v){
        if(nome.getText().toString().equals("") || crm.getText().toString().equals("") || logradouro.getText().toString().equals("") ||
                numero.getText().toString().equals("") || cidade.getText().toString().equals("") || stringUf.equals("") ||
                celular.getText().toString().equals("") || fixo.getText().toString().equals("")){
            Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_LONG).show();
        }else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

            StringBuilder sql_builder = new StringBuilder();
            sql_builder.append("UPDATE medico SET ");
            sql_builder.append("nome = '" + nome.getText().toString() + "', ");
            sql_builder.append("crm = '" + crm.getText().toString() + "', ");
            sql_builder.append("logradouro = '" + logradouro.getText().toString() + "', ");
            sql_builder.append("numero = " + numero.getText().toString() + ", ");
            sql_builder.append("cidade = '" + cidade.getText().toString() + "', ");
            sql_builder.append("uf = '" + stringUf + "', ");
            sql_builder.append("celular = '" + celular.getText().toString() + "', ");
            sql_builder.append("fixo = '" + fixo.getText().toString() + "'");
            sql_builder.append("WHERE _id = " + _id + ";");

            try {
                db.execSQL(sql_builder.toString());
                Toast.makeText(this, "Editado", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            Intent i = new Intent(getApplicationContext(), ListarMedico.class);
            startActivity(i);

            db.close();
        }
    }

    public void excluirMedico(View v){

        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("DELETE FROM medico ");
        sql_builder.append("WHERE _id = " + _id + ";");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Excluido", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), ListarMedico.class);
        startActivity(i);

        db.close();
    }

}