package Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = "TEXT";
    private static final  String SQL_CREATE_USUARIO = "CREATE TABLE " +
            DefineTabla.Usuarios.TABLE_NAME+"("+DefineTabla.Usuarios.COLUM_NAME_ID+" INTEGER PRIMARY KEY, "+
            DefineTabla.Usuarios.COLUM_NAME_NOMBRE+" "+TEXT_TYPE+","+
            DefineTabla.Usuarios.COLUM_NAME_EMAIL+" "+TEXT_TYPE+","+
            DefineTabla.Usuarios.COLUM_NAME_PASSWORD+" "+TEXT_TYPE +");";

    private static final String SQL_DELETE_USUARIO =" DROP TABLE IF EXISTS "+ DefineTabla.Usuarios.TABLE_NAME;

    private static final String DATABASE_NAME = "sistema.db";
    private static final int DATABASE_VERSION = 1;

    public UsuarioDBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USUARIO);
        onCreate(db);
    }
}
