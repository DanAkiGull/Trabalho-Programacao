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
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdicionarConsulta extends AppCompatActivity {

    Spinner spPaciente;
    ArrayList<String> pacienteNome;
    ArrayList<String> pacienteId;
    int indicePaciente;

    Spinner spMedico;
    ArrayList<String> medicoNome;
    ArrayList<String> medicoId;
    int indiceMedico;

    TextView addInicioCon;
    TextView addFimCon;
    TextView addObsCon;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_consulta);

        spPaciente = findViewById(R.id.addPacienteCon);
        spMedico = findViewById(R.id.addMedicoCon);
        addInicioCon = findViewById(R.id.addInicioCon);
        addFimCon = findViewById(R.id.addFimCon);
        addObsCon = findViewById(R.id.addObsCon);

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


    }

    public void adicionarConsulta(View v){
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();
        sql_builder.append("INSERT INTO consulta (paciente_id, medico_id, data_hora_inicio, data_hora_fim, observacao) VALUES ( ");
        sql_builder.append( pacienteId.get(indicePaciente) + ", ");
        sql_builder.append( medicoId.get(indiceMedico) + ", ");
        sql_builder.append("'"+addInicioCon.getText().toString()+"' , ");
        sql_builder.append("'" + addFimCon.getText().toString() + "' , ");
        sql_builder.append("'"+ addObsCon.getText().toString() +"');");

       // sql_builder.append("'"+ addInicioCon +"' , ");
        //sql_builder.append("'"+ addFimCon +"' , ");

        try {
            db.execSQL(sql_builder.toString());
            Toast.makeText(this,"Adicionado", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(this,"Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(i);

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