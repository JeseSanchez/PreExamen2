package Modelo;

public class DefineTabla {
    public DefineTabla(){
    }
    public static abstract class Usuarios{
        public static final String  TABLE_NAME = "usuarios";
        public static final String  COLUM_NAME_ID = "id";
        public static final String  COLUM_NAME_NOMBRE = "nombre";
        public static final String  COLUM_NAME_EMAIL = "email";
        public static final String  COLUM_NAME_PASSWORD = "password";

        public static  String[] REGISTRO = new String[]{
                Usuarios.COLUM_NAME_ID,
                Usuarios.COLUM_NAME_NOMBRE,
                Usuarios.COLUM_NAME_EMAIL,
                Usuarios.COLUM_NAME_PASSWORD
        };
    }
}