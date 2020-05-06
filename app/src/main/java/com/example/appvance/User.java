package com.example.appvance;

/**
 * Clase que crea los usuarios
 * @author Miguel Prieto Horcajo
 */
public class User {

    public String id;
    public String nombre;
    public String pass;

    /**
     * Constructor de la clase
     * @param id ID del usuario
     * @param nombre Nombre del usuario
     * @param pass Contraseña del usuario
     */
    public User(String id, String nombre, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
    }
}
