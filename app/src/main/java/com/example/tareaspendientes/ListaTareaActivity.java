package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListaTareaActivity extends AppCompatActivity {
    private ListView listaTareas;
    private TextView nombre;
    private Button btnAgregar;
    private RadioButton rbtn1, rbtn2, rbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarea);

        Inits();
        Bundles();
        btnAction();
    }

    private void Inits(){
        listaTareas = findViewById(R.id.lstTareaCliente);
        btnAgregar = findViewById(R.id.btnTareaNueva);
    }

    private void btnAction(){
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event(v);
            }
        });
    }

    private void Bundles(){
        Bundle b = getIntent().getExtras();
        if(b != null){
            String nombreUsuario;
            nombreUsuario = b.getString("keyUser");
            nombre.setText(nombreUsuario);
        }
    }

    private void event(View v){
        if(v.getId() == R.id.btnTareaNueva){
            Intent tarea = new Intent(this, TareaActivity.class);
            startActivity(tarea);
        }
    }
}