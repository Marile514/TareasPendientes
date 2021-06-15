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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        btnActions();
    }

    private void inits(){
        user = findViewById(R.id.tilUser);
        contrasena = findViewById(R.id.tilPassword);
        acceso = findViewById(R.id.btnIniciar);
        fuera = findViewById(R.id.btnSalir);
    }

    //Metodo para manejar el correo de usuarios.
    private boolean aceptarUsuario(TextInputEditText user){
        if(!user.getText().toString().isEmpty()){
            return true;
        } else {
            user.setError("Correo o usuario no ingresada o vacía");
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

            if(aceptarUsuario(user) && aceptarContrasena(contrasena)){
                limpiarCampos();
                Intent i = new Intent(this, TareaActivity.class);
                startActivity(i);
            }
        }
        if(v.getId() == R.id.btnSalir){
            finish();
        }
    }

    private void crearUsers(){
        try {
            OutputStreamWriter u = new OutputStreamWriter(openFileOutput("usersLog.txt", Activity.MODE_APPEND));
            u.write("Usuario, Contraseña\n");
            u.write("Luchito, cloudwolf\n");
            u.write("The venom, admin\n");
            u.close();
            Toast.makeText(MainActivity.this, "Guardado el archivo txt", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Log.e("TAG_", ex.toString());
        }
    }

    private void leerUsers(){
        try{
            InputStreamReader readUser = new InputStreamReader(openFileInput("userLog.txt"));
            BufferedReader lu = new BufferedReader(readUser);

            String linea;
            do{
                linea = lu.readLine();
                if(linea != null){
                    users.add(user, contrasena);
                }

            }while (linea != null);
            lu.close();
            readUser.close();
        }catch(Exception ex){
            Log.e("TAG_", ex.toString());
        }
    }



    private void limpiarCampos(){
        user.setText("");
        contrasena.setText("");
    }
}