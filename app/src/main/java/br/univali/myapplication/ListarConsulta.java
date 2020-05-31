package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListarConsulta extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_consulta);

        lvConsultas = findViewById(R.id.lvConsultas);

        listarConsultas();

    }

    private void listarConsultas() {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        sql_builder.append("SELECT * FROM consulta;");
        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        String[] from = {"_id", "paciente_id", "medico_id", "data_hora_inicio", "data_hora_fim", "observacao"};
        int[] to = {R.id.tvListIdConsulta, R.id.tvListIdPaciente, R.id.tvListIdMedico, R.id.tvListInicioConsulta,
                R.id.tvListFimConsulta, R.id.tvListObservacaoConsulta};

        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.dados_consulta, dados, from, to, 0);

        lvConsultas.setAdapter(scAdapter);

        db.close();
    }
}