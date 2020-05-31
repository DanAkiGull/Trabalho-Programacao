package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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