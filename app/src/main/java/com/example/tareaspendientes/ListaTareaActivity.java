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
    private RadioButton rbPendiente, rbRealizada, rbTodas;
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
        rbPendiente = findViewById(R.id.rb1);
        rbRealizada = findViewById(R.id.rb2);
        rbTodas = findViewById(R.id.rb3);

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
                    tarea.add(new Tareas(tareaString[0], tareaString[1], tareaString[2], tareaString[3], tareaString[4], tareaString[5]));
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
        //Implementar método de los radio button.
        rbRealizada.setOnClickListener(new View.OnClickListener() { //RadioButton de realizadas las tareas.
            @Override
            public void onClick(View v) {
                ArrayList<Tareas> listasRealizadas = new ArrayList<>();
                for(Tareas t: listaTareasUsuario){
                    if(t.getEstado().equals("lista")){
                        listasRealizadas.add(t);
                    }
                    ArrayAdapter adpListRelease = new ArrayAdapter(ListaTareaActivity.this, android.R.layout.simple_list_item_1, listasRealizadas);
                    listaTareas.setAdapter(adpListRelease);
                }
            }
        });
        //RadioButton de tareas pendientes.
        rbPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Tareas> listasPendientes = new ArrayList<>();
                for(Tareas t: listaTareasUsuario){
                    if(t.getEstado().equals("pendiente")){
                        listasPendientes.add(t);
                    }
                    ArrayAdapter adpListPend = new ArrayAdapter(ListaTareaActivity.this, android.R.layout.simple_list_item_1, listasPendientes);
                    listaTareas.setAdapter(adpListPend);
                }
            }
        });
        //RadioButton de todas (mostrar todas las tareas de los usuarios ingresados).
        rbTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Tareas> listasTodas = new ArrayList<>();

            }
        });
    }

    private void event(View v){
        if(v.getId() == R.id.btnTareaNueva){
           tareasUsuarioLogear();
            //Intent tarea = new Intent(this, TareaActivity.class);
            //startActivity(tarea);
        }
    }
}