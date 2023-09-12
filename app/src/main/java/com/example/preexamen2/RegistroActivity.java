package com.example.preexamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Modelo.UsuarioDB;
import Modelo.UsuarioDBHelper;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombre,
                     txtCorreo,
                     txtContraseña,
                     txtRecontraseña;

    private Button   btnRegistrar,
                     btnRegresar;

    private UsuarioDB database;
    private UsuarioDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre       = findViewById(R.id.txtNombre      );
        txtCorreo       = findViewById(R.id.txtCorreo      );
        txtContraseña   = findViewById(R.id.txtContraseña  );
        txtRecontraseña = findViewById(R.id.txtRecontraseña);

        btnRegistrar    = findViewById(R.id.btnRegistrar   );
        btnRegresar     = findViewById(R.id.btnRegresar    );
        helper= new UsuarioDBHelper(this);
        database = new UsuarioDB(this,helper);

        btnRegistrar.setOnClickListener(v ->{
            if(txtNombre.getText().toString().equals("")||
                    txtCorreo.getText().toString().equals("")||
                    txtContraseña.getText().toString().equals("")||
                    txtRecontraseña.getText().toString().equals("")){

                Toast.makeText(this, "Faltan datos por completar", Toast.LENGTH_SHORT).show();

            }
            else if(!txtContraseña.getText().toString().equals(txtRecontraseña.getText().toString())){
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
            else{
                Usuario usuario = new Usuario(txtNombre.getText().toString(),txtCorreo.getText().toString(),txtContraseña.getText().toString());
                database.insetUsuario(usuario);
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnRegresar.setOnClickListener(v -> finish());
    }
}