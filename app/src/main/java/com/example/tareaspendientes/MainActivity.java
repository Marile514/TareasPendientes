package com.example.tareaspendientes;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText correo, contrasena;
    private Button acceso, fuera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        btnActions();
    }

    private void inits(){
        correo = findViewById(R.id.tilUser);
        contrasena = findViewById(R.id.tilPassword);
        acceso = findViewById(R.id.btnIniciar);
        fuera = findViewById(R.id.btnSalir);
    }

    //Metodo para manejar el correo de usuarios.
    private boolean aceptarCorreo(TextInputEditText correo){
        if(!correo.getText().toString().isEmpty()){
            String strCorreo = correo.getText().toString();
            //Usar el método validarCorreo2(strCorreo) para la expresión regular.
            if(validarCorreo(strCorreo)){
                return true;
            } else {
                correo.setError("No es un Correo!");
                return false;
            }
        } else {
            correo.setError("Correo vacío");
            return false;
        }
    }

    public boolean validarCorreo(String mail){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.find();
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
            aceptarCorreo(correo);
            aceptarContrasena(contrasena);

            if(aceptarCorreo(correo) && aceptarContrasena(contrasena)){
                limpiarCampos();
                Intent i = new Intent(this, TareaActivity.class);
                startActivity(i);
            }
        }
        if(v.getId() == R.id.btnSalir){
            finish();
        }
    }

    private void limpiarCampos(){
        correo.setText("");
        contrasena.setText("");
    }
}