package com.example.preexamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Modelo.DefineTabla;
import Modelo.UsuarioDB;

public class MainActivity extends AppCompatActivity {

    private EditText   txtCorreo,
                       txtContraseña;

    private Button     btnIngresar,
                       btnRegistrarme,
                       btnCerrar;


    private UsuarioDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorreo      = findViewById(R.id.txtCorreo     );
        txtContraseña  = findViewById(R.id.txtContraseña );

        btnIngresar    = findViewById(R.id.btnIngresar   );
        btnRegistrarme = findViewById(R.id.btnRegistrarme);
        btnCerrar      = findViewById(R.id.btnCerrar     );
        database = new UsuarioDB(this);



        btnIngresar.setOnClickListener(v -> {
            Cursor cursor = database.getAllData();
            int i=0;
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    if ((cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Usuarios.COLUM_NAME_EMAIL)).equals(txtCorreo.getText().toString())) &&
                            (cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Usuarios.COLUM_NAME_PASSWORD)).equals(txtContraseña.getText().toString()))) {
                        startActivity(new Intent(MainActivity.this, ListaActivity.class));
                        i = 1;
                    }
                }while (cursor.moveToNext());
                if (i ==0)
                    Toast.makeText(this, "Usuario o contraseña no encontrado", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistrarme.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegistroActivity.class)));

        btnCerrar.setOnClickListener(v -> finishAndRemoveTask());
    }
}