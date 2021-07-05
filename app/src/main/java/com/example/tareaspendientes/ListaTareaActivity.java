package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListaTareaActivity extends AppCompatActivity {
    private ListView listaTareas;
    private TextView nombre;
    private Button btnAgregar;
    private RadioButton rbtn1, rbtn2, rbtn3;
    private ArrayAdapter tareas;
    private ArrayList<Tareas> tarea = new ArrayList();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarea);

        inits();
        bundles();
        listarTareas();
        btnAction();
    }

    private void inits(){
        listaTareas = findViewById(R.id.lstTareaCliente);
        btnAgregar = findViewById(R.id.btnTareaNueva);

        //tareas = new ArrayAdapter(ListaTareaActivity.this, android.R.layout.simple_list_item_1, tarea);
        listaTareas.setAdapter(tareas);
    }

    private void btnAction(){
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event(v);
            }
        });
    }

    private void bundles(){
        Bundle b = getIntent().getExtras();
        if(b != null){
            usuario = (Usuario) b.getSerializable("keyUser");
            //nombre.setText(usuario.getUsuario());
        }
    }

    private void listarTareas(){
        try{
            InputStreamReader reader = new InputStreamReader(openFileInput("tareasUsuario.txt"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;
            do{
                linea = bufferedReader.readLine();
                if(linea != null){
                    String[] tareaString = linea.split(", ");
                    tarea.add(new Tareas(tareaString[0], tareaString[1], tareaString[2], tareaString[3], tareaString[4]));
                }
            }while (linea != null);
            reader.close();
            bufferedReader.close();
        }catch (Exception ex){
            Log.d("TAG_", "listaTareas: error" +ex.toString());
        }
    }

    private void tareasUsuarioLogear(){
        ArrayList<Tareas> listaTareasUsuario = new ArrayList<>();
        for(Tareas t: tarea){
            if(t.getUser().equals(usuario.getUsuario())){
                listaTareasUsuario.add(t);
            }
        }
        ArrayAdapter listAdp = new ArrayAdapter(ListaTareaActivity.this, android.R.layout.simple_list_item_1, listaTareasUsuario);
        listaTareas.setAdapter(listAdp);
        Log.d("TASK_", "tareasUsuarioLogear: " + listaTareasUsuario);
    }

    private void event(View v){
        if(v.getId() == R.id.btnTareaNueva){
           tareasUsuarioLogear();
            //Intent tarea = new Intent(this, TareaActivity.class);
            //startActivity(tarea);
        }
    }
}