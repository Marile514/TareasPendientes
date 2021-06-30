package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TareaActivity extends AppCompatActivity {
    private EditText titulo, observacion, fecha_inicio, fecha_fin;
    private RadioButton rbtnPendiente, rbtnRealize;
    private Button btnActual, btnDelete;
    private ArrayList<Tareas> tarea = new ArrayList<Tareas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        Inits();
        buttons();
    }

    private void Inits(){
        titulo = findViewById(R.id.etTitulo);
        fecha_inicio = findViewById(R.id.etdFecha1);
        fecha_fin = findViewById(R.id.etdFecha2);
        observacion = findViewById(R.id.etObserv);
        rbtnPendiente = findViewById(R.id.rbtn1);
        rbtnRealize = findViewById(R.id.rbtn2);
        btnActual = findViewById(R.id.btnActualizar);
        btnDelete = findViewById(R.id.btnEliminar);
    }

    //Métodos para validar las fechas de inicio y fin.
    public boolean validarFechaInicio(EditText fecha_inicio){
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(String.valueOf(fecha_inicio));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean validarFechaFin(EditText fecha_fin){
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(String.valueOf(fecha_fin));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    //------------------------------------------------------------
    private boolean validarTitulo(EditText titulo){
        if(!titulo.getText().toString().isEmpty()){
            return true;
        }else{
            titulo.setError("Titulo no ingresado");
            return false;
        }
    }

    private boolean validarObservacion(EditText observacion){
        if(!observacion.getText().toString().isEmpty()){
            return true;
        }else{
            observacion.setError("Observacion no ingresado");
            return false;
        }
    }

    private void buttons(){
        btnActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonEvents(v);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               buttonEvents(v);
            }
        });
    }

    private void buttonEvents(View view){
        if(view.getId() == R.id.btnActualizar){
            if(validarTitulo(titulo) && validarFechaInicio(fecha_inicio) && validarFechaFin(fecha_fin) && validarObservacion(observacion) ){
                Toast.makeText(TareaActivity.this, "Datos a ser transferidos", Toast.LENGTH_SHORT).show();
                try{
                    OutputStreamWriter c = new OutputStreamWriter(openFileOutput("tareasUsuario.txt", Activity.MODE_PRIVATE));
                    c.write("Titulo: " +titulo.getText());
                    c.write("\n Fecha inicio: " +fecha_inicio.getText());
                    c.write("\n Fecha fin: " +fecha_fin.getText());
                    c.write("\n Observación: " +observacion.getText());
                    c.close();
                    Toast.makeText(TareaActivity.this, "Tareas guardadas", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("TAG_", e.toString());
                }
                tarea.add(new Tareas())
                //Realizar instancia hacia la ListaTareaActivity.
                Intent work = new Intent(TareaActivity.this, ListaTareaActivity.class);
                startActivity(work);
            }
        }else{
            if(view.getId() == R.id.btnEliminar){

            }
        }
    }
}