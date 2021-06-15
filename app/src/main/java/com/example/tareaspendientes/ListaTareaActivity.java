package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ListaTareaActivity extends AppCompatActivity {
    private ListView listaTareas;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarea);
    }

    private void Inits(){
        listaTareas = findViewById(R.id.lstTareaCliente);
        btnAgregar = findViewById(R.id.btnTareaNueva);
    }
}