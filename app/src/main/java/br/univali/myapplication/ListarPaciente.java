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

public class ListarPaciente extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        lvPacientes = findViewById(R.id.lvPacientes);

        listarPacientes();

        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View v = lvPacientes.getChildAt(position);
                TextView tvListIdPaciente = v.findViewById(R.id.tvListIdPaciente);
                TextView tvListNomePaciente = v.findViewById(R.id.tvListNomePaciente);
                TextView tvListCrmPaciente = v.findViewById(R.id.tvListGrpPaciente);
                TextView tvListLogradouroPaciente = v.findViewById(R.id.tvListLogradouroPaciente);
                TextView tvListNumeroPaciente = v.findViewById(R.id.tvListNumeroPaciente);
                TextView tvListCidadePaciente = v.findViewById(R.id.tvListCidadePaciente);
                TextView tvListUfPaciente = v.findViewById(R.id.tvListUfPaciente);
                TextView tvListCelularPaciente = v.findViewById(R.id.tvListCelularPaciente);
                TextView tvListFixoPaciente = v.findViewById(R.id.tvListFixoPaciente);

                Intent i = new Intent(getApplicationContext(), EditarPaciente.class);

                i.putExtra("_id",tvListIdPaciente.getText().toString());
                i.putExtra("nome",tvListNomePaciente.getText().toString());
                i.putExtra("grp_sanguineo",tvListCrmPaciente.getText().toString());
                i.putExtra("logradouro",tvListLogradouroPaciente.getText().toString());
                i.putExtra("numero",tvListNumeroPaciente.getText().toString());
                i.putExtra("cidade",tvListCidadePaciente.getText().toString());
                i.putExtra("uf",tvListUfPaciente.getText().toString());
                i.putExtra("celular",tvListCelularPaciente.getText().toString());
                i.putExtra("fixo",tvListFixoPaciente.getText().toString());

                startActivity(i);
            }
        });
    }

    private void listarPacientes() {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        sql_builder.append("SELECT * FROM paciente;");
        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        String[] from = {"_id", "nome", "grp_sanguineo", "logradouro", "numero", "cidade", "uf", "celular", "fixo"};
        int[] to = {R.id.tvListIdPaciente, R.id.tvListNomePaciente, R.id.tvListGrpPaciente, R.id.tvListLogradouroPaciente,
                R.id.tvListNumeroPaciente, R.id.tvListCidadePaciente, R.id.tvListUfPaciente, R.id.tvListCelularPaciente,
                R.id.tvListFixoPaciente };

        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.dados_pacientes, dados, from, to, 0);

        lvPacientes.setAdapter(scAdapter);

        db.close();
    }
}