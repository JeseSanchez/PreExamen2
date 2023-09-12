package Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.preexamen2.Usuario;

import java.util.ArrayList;

public class UsuarioDB implements Persitencia, Proyeccion{
    private Context context;
    private UsuarioDBHelper helper;
    private SQLiteDatabase db;

    public UsuarioDB(Context context, UsuarioDBHelper helper){
        this.context = context;
        this.helper =helper;
    }

    public UsuarioDB(Context context){
        this.context = context;
        this.helper =new UsuarioDBHelper(this.context);
    }

    @Override
    public void openDataBase() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void closeDataBase() {
        helper.close();
    }

    @Override
    public long insetUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DefineTabla.Usuarios.COLUM_NAME_NOMBRE,usuario.getNombre());
        values.put(DefineTabla.Usuarios.COLUM_NAME_EMAIL,usuario.getEmail());
        values.put(DefineTabla.Usuarios.COLUM_NAME_PASSWORD,usuario.getPassword());

        this.openDataBase();
        long num = db.insert(DefineTabla.Usuarios.TABLE_NAME,null,values);
        this.closeDataBase();
        return num;
    }

    @Override
    public long updateUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DefineTabla.Usuarios.COLUM_NAME_NOMBRE,usuario.getNombre());
        values.put(DefineTabla.Usuarios.COLUM_NAME_EMAIL,usuario.getEmail());
        values.put(DefineTabla.Usuarios.COLUM_NAME_PASSWORD,usuario.getPassword());

        this.openDataBase();
        long num = db.update(DefineTabla.Usuarios.TABLE_NAME,values,DefineTabla.Usuarios.COLUM_NAME_ID + usuario.getId(),null);
        this.closeDataBase();
        return num;
    }

    @Override
    public void deleteUsuario(int id) {
        this.openDataBase();
        db.delete(DefineTabla.Usuarios.TABLE_NAME,DefineTabla.Usuarios.COLUM_NAME_ID+"=?",new String[]{String.valueOf(id)});
        this.closeDataBase();
    }

    public void delete() {
        this.openDataBase();
        db.delete(DefineTabla.Usuarios.TABLE_NAME,null,null);
        this.closeDataBase();
    }

    @Override
    public Usuario getUsuario(String NOMBRE) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(DefineTabla.Usuarios.TABLE_NAME,DefineTabla.Usuarios.REGISTRO,DefineTabla.Usuarios.COLUM_NAME_NOMBRE+"=?",new String[]{NOMBRE},null,null,null);
        cursor.moveToFirst();
        Usuario alumno = readUsuario(cursor);
        return alumno;
    }

    @Override
    public ArrayList<Usuario> AllUsuarios() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(DefineTabla.Usuarios.TABLE_NAME,DefineTabla.Usuarios.REGISTRO,null,null,null,null,null);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.isAfterLast()){
            Usuario usuario = readUsuario(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return usuarios;
    }

    @Override
    public Usuario readUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setNombre(cursor.getString(1));
        usuario.setEmail(cursor.getString(2));
        usuario.setPassword(cursor.getString(3));
        return usuario;
    }

    public Cursor getAllData() {
        db = helper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + DefineTabla.Usuarios.TABLE_NAME, null);
    }
}
