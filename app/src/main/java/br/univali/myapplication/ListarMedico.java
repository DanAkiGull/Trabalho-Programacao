package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListarMedico extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_medico);

        lvMedicos = findViewById(R.id.lvMedicos);

        listarMedicos();

        lvMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View v = lvMedicos.getChildAt(position);
                TextView tvListIdMedico = v.findViewById(R.id.tvListIdMedico);
                TextView tvListNomeMedico = v.findViewById(R.id.tvListNomeMedico);
                TextView tvListCrmMedico = v.findViewById(R.id.tvListCrmMedico);
                TextView tvListLogradouroMedico = v.findViewById(R.id.tvListLogradouroMedico);
                TextView tvListNumeroMedico = v.findViewById(R.id.tvListNumeroMedico);
                TextView tvListCidadeMedico = v.findViewById(R.id.tvListCidadeMedico);
                TextView tvListUfMedico = v.findViewById(R.id.tvListUfMedico);
                TextView tvListCelularMedico = v.findViewById(R.id.tvListCelularMedico);
                TextView tvListFixoMedico = v.findViewById(R.id.tvListFixoMedico);

                Intent i = new Intent(getApplicationContext(), EditarMedico.class);

                i.putExtra("_id",tvListIdMedico.getText().toString());
                i.putExtra("nome",tvListNomeMedico.getText().toString());
                i.putExtra("crm",tvListCrmMedico.getText().toString());
                i.putExtra("logradouro",tvListLogradouroMedico.getText().toString());
                i.putExtra("numero",tvListNumeroMedico.getText().toString());
                i.putExtra("cidade",tvListCidadeMedico.getText().toString());
                i.putExtra("uf",tvListUfMedico.getText().toString());
                i.putExtra("celular",tvListCelularMedico.getText().toString());
                i.putExtra("fixo",tvListFixoMedico.getText().toString());

                startActivity(i);
            }
        });

    }

    private void listarMedicos() {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);

        StringBuilder sql_builder =  new StringBuilder();

        sql_builder.append("SELECT * FROM medico;");
        Cursor dados = db.rawQuery(sql_builder.toString(),null);

        String[] from = {"_id", "nome", "crm", "logradouro", "numero", "cidade", "uf", "celular", "fixo"};
        int[] to = {R.id.tvListIdMedico, R.id.tvListNomeMedico, R.id.tvListCrmMedico, R.id.tvListLogradouroMedico,
                    R.id.tvListNumeroMedico, R.id.tvListCidadeMedico, R.id.tvListUfMedico, R.id.tvListCelularMedico,
                    R.id.tvListFixoMedico };

       SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.dados_medicos, dados, from, to, 0);

        lvMedicos.setAdapter(scAdapter);

        db.close();
    }


}