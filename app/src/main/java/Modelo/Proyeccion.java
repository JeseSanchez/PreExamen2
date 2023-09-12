package Modelo;

import android.database.Cursor;

import com.example.preexamen2.Usuario;

import java.util.ArrayList;

public interface Proyeccion {
    public Usuario getUsuario(String nombre);
    public ArrayList<Usuario> AllUsuarios();
    public Usuario readUsuario(Cursor cursor);
}
