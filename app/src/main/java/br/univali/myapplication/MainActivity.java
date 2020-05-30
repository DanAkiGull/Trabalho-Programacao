package br.univali.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_addMedico = (Button)findViewById(R.id.btn_addMedico);
        btn_addMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, AdicionarMedico.class);
                    startActivity(i);
            }
        });

        Button btn_addPaciente = (Button)findViewById(R.id.btn_addPaciente);
        btn_addPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AdicionarPaciente.class);
                startActivity(i);
            }
        });

        Button btn_addConsulta = (Button)findViewById(R.id.btn_addConsulta);
        btn_addConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AdicionarConsulta.class);
                startActivity(i);
            }
        });
    }
}