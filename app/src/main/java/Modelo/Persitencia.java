package Modelo;


import com.example.preexamen2.Usuario;

public interface Persitencia {
    public void openDataBase();
    public void closeDataBase();
    public long insetUsuario(Usuario usuario);
    public long updateUsuario(Usuario usuario);
    public void deleteUsuario(int id);
}
