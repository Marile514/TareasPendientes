package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText user, contrasena;
    private Button acceso, fuera;
    private ArrayList<Usuario> users = new ArrayList<Usuario>();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        btnActions();
        leerUsers();
    }

    private void inits(){
        user = findViewById(R.id.tilUser);
        contrasena = findViewById(R.id.tilPassword);
        acceso = findViewById(R.id.btnIniciar);
        fuera = findViewById(R.id.btnSalir);
        crearUsers();
        crearTareastxt();
    }

    //Metodo para manejar el correo de usuarios.
    private boolean aceptarUsuario(TextInputEditText user){
        if(!user.getText().toString().isEmpty()){
            return true;
        } else {
            user.setError("Usuario no ingresada o vacía");
            return false;
        }
    }

    private boolean aceptarContrasena(TextInputEditText contrasena){
        if(!contrasena.getText().toString().isEmpty()){
            return true;
        } else {
            contrasena.setError("Contraseña no ingresada o vacía");
            return false;
        }
    }

    private void btnActions(){
        acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonsEvents(v);
            }
        });

        fuera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonsEvents(v);
            }
        });
    }

    private void botonsEvents(View v){
        if(v.getId() == R.id.btnIniciar){
            //Para hacer que el boton presionado acceda hacia otra clase.
            aceptarUsuario(user);
            aceptarContrasena(contrasena);
            String userString = user.getText().toString();
            String passwordString = contrasena.getText().toString();
            if(aceptarUsuario(user) && aceptarContrasena(contrasena)){
                for(Usuario u: users){
                    if(u.getUsuario().equals(userString) && u.getPassword().equals(passwordString)){
                        usuario = u;
                        Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
                limpiarCampos();
                Intent i = new Intent(MainActivity.this, ListaTareaActivity.class);
                i.putExtra("keyUser", usuario);
                startActivity(i);
            }
        }
        if(v.getId() == R.id.btnSalir){
            finish();
        }
    }

    private void crearUsers(){
        try {
            OutputStreamWriter u = new OutputStreamWriter(openFileOutput("usersLog.txt", Activity.MODE_PRIVATE));
            u.write("Luchito, cloudwolf\n");
            u.write("Vixcho, admin");
            u.close();
            Toast.makeText(MainActivity.this, "Guardado el archivo txt", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Log.e("TAG_", ex.toString());
        }
    }

    private void leerUsers(){
        try{
            InputStreamReader readUser = new InputStreamReader(openFileInput("usersLog.txt"));
            BufferedReader lu = new BufferedReader(readUser);

            String linea;
            do{
                linea = lu.readLine();
                if(linea != null){
                    Log.d("TAG_","Leer usuarios: "+linea);
                    String[] loginRegistrado = linea.split(", ");
                    users.add(new Usuario(loginRegistrado[0], loginRegistrado[1]));
                }

            }while (linea != null);
            lu.close();
            readUser.close();
            Log.d("USR_", users.toString());
        }catch(Exception ex){
            Log.e("TAG_", ex.toString());
        }
    }

    private void crearTareastxt(){
        try{
            OutputStreamWriter c = new OutputStreamWriter(openFileOutput("tareasUsuario.txt", Activity.MODE_PRIVATE));
            c.write("Luchito, tarea1, 04-07-2021, 10-07-2021, Observacion1, lista\n");
            c.write("Luchito, tarea2, 04-07-2021, 10-07-2021, Observacion2, pendiente\n");
            c.write("Vixcho, tarea3, 04-07-2021, 10-07-2021, Observacion3, lista\n");
            c.write("Vixcho, tarea4, 04-07-2021, 10-07-2021, Observacion4, pendiente");
        }catch (Exception e){
            Log.e("TAG_", e.toString());
        }
    }

    private void limpiarCampos(){
        user.setText("");
        contrasena.setText("");
    }
}