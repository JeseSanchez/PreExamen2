package com.example.preexamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Modelo.DefineTabla;
import Modelo.UsuarioDB;

public class ListaActivity extends AppCompatActivity {

    private UsuarioDB database = new UsuarioDB(this);
    private ListView lstUsuarios;
    private ArrayList<String> arrayList;
    private Button   btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstUsuarios = findViewById(R.id.lstUsuarios);
        btnRegresar = findViewById(R.id.btnRegresar);

        arrayList = new ArrayList<>();

        Cursor cursor = database.getAllData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String concatenada = cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Usuarios.COLUM_NAME_NOMBRE));
                arrayList.add(concatenada);
            }
            cursor.close();
        }

        lstUsuarios.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,arrayList));

        btnRegresar.setOnClickListener(v -> finish());
    }

}