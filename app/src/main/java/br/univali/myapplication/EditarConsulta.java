package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarConsulta extends AppCompatActivity {


    Spinner spPaciente;
    ArrayList<String> pacienteNome;
    ArrayList<String> pacienteId;
    int indicePaciente;

    Spinner spMedico;
    ArrayList<String> medicoNome;
    ArrayList<String> medicoId;
    int indiceMedico;

    TextView editInicioCon;
    TextView editFimCon;
    TextView editObsCon;

    String _id;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_consulta);

        spPaciente = findViewById(R.id.editPacienteCon);
        spMedico = findViewById(R.id.editMedicoCon);
        editInicioCon = findViewById(R.id.editInicioCon);
        editFimCon = findViewById(R.id.editFimCon);
        editObsCon = findViewById(R.id.editObsCon);

        acharPacientes();
        acharMedico();

        ArrayAdapter<String> spPacienteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pacienteNome);
        spPaciente.setAdapter(spPacienteAdapter);

        spPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indicePaciente = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                indicePaciente = -1;
            }
        });

        ArrayAdapter<String> spMedicoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, medicoNome);
        spMedico.setAdapter(spMedicoAdapter);

        spMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indiceMedico = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                indiceMedico = -1;
            }
        });

        Intent valores =  getIntent();

        _id = valores.getStringExtra("_id");

        String selecao = valores.getStringExtra("paciente_id");
        int aux = 0;
        for (String c : pacienteId ){
            if(c.equals(selecao)){
                break;
            }
            aux++;
        }
        spPaciente.setSelection(aux);

        selecao = valores.getStringExtra("medico_id");
        aux = 0;
        for (String c : medicoId ){
            if(c.equals(selecao)){
                break;
            }
            aux++;
        }
        spMedico.setSelection(aux);
        editInicioCon.setText(valores.getStringExtra("data_hora_inicio"));
        editFimCon.setText(valores.getStringExtra("data_hora_fim"));
        editObsCon.setText(valores.getStringExtra("observacao"));

    }

    public void editarConsulta(View v){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("UPDATE paciente SET ");
        sql_builder.append( " paciente_id = " + pacienteId.get(indicePaciente) + ", ");
        sql_builder.append(" medico_id = " + medicoId.get(indiceMedico) + ", ");
        sql_builder.append("data_hora_inicio = '"+ editInicioCon.getText().toString()+"' , ");
        sql_builder.append("data_hora_fim = '" + editFimCon.getText().toString() + "' , ");
        sql_builder.append("observacao = '"+ editObsCon.getText().toString() +"' ");
        sql_builder.append("WHERE  _id = " + _id + ";");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Editado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

        db.close();
    }

    public void excluirConsulta(View v){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("DELETE FROM consulta ");
        sql_builder.append("WHERE  _id = " + _id + ";");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Excluido", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

        db.close();
    }


    public void acharPacientes(){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        pacienteNome = new ArrayList<>();
        pacienteId = new ArrayList<>();

        sql_builder.append("SELECT nome, _id FROM paciente;");

        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        if(dados.moveToFirst()){
            do {
                pacienteNome.add(dados.getString(0));
                pacienteId.add(dados.getString(1));
            } while (dados.moveToNext());
        }

        dados.close();
        db.close();

    }

    public void acharMedico(){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        medicoNome = new ArrayList<>();
        medicoId = new ArrayList<>();

        sql_builder.append("SELECT nome, _id FROM medico;");

        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        if(dados.moveToFirst()){
            do {
                medicoNome.add(dados.getString(0));
                medicoId.add(dados.getString(1));
            } while (dados.moveToNext());
        }

        dados.close();
        db.close();

    }


}