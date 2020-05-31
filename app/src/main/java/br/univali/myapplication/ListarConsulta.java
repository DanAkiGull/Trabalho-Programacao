package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListarConsulta extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_consulta);

        lvConsultas = findViewById(R.id.lvConsultas);

        listarConsultas();

        lvConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View v = lvConsultas.getChildAt(position);
                TextView tvListIdConsulta = v.findViewById(R.id.tvListIdConsulta);
                TextView tvListIdPaciente = v.findViewById(R.id.tvListPacienteConsulta);
                TextView tvListIdMedico = v.findViewById(R.id.tvListMedicoConsulta);
                TextView tvListInicioConsulta = v.findViewById(R.id.tvListInicioConsulta);
                TextView tvListFimConsulta = v.findViewById(R.id.tvListFimConsulta);
                TextView tvListObservacaoConsulta = v.findViewById(R.id.tvListObservacaoConsulta);

                Intent i = new Intent(getApplicationContext(), EditarConsulta.class);

                i.putExtra("_id",tvListIdConsulta.getText().toString());
                i.putExtra("paciente_id",tvListIdPaciente.getText().toString());
                i.putExtra("medico_id",tvListIdMedico.getText().toString());
                i.putExtra("data_hora_inicio",tvListInicioConsulta.getText().toString());
                i.putExtra("data_hora_fim",tvListFimConsulta.getText().toString());
                i.putExtra("observacao",tvListObservacaoConsulta.getText().toString());

                startActivity(i);
            }
        });

    }

    private void listarConsultas() {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        sql_builder.append("SELECT * FROM consulta;");
        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        String[] from = {"_id", "paciente_id", "medico_id", "data_hora_inicio", "data_hora_fim", "observacao"};
        int[] to = {R.id.tvListIdConsulta, R.id.tvListPacienteConsulta, R.id.tvListMedicoConsulta, R.id.tvListInicioConsulta,
                R.id.tvListFimConsulta, R.id.tvListObservacaoConsulta};

        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.dados_consulta, dados, from, to, 0);

        lvConsultas.setAdapter(scAdapter);

        db.close();
    }
}