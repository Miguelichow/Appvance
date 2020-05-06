package com.example.appvance;

/**
 * Clase que maneja lo ejercicios
 * @author Miguel Prieto Horcajo
 */
public class Exercise {
    public String nombre, sets, reps;

    /**
     * Constructor de la clase
     * @param nombre
     * @param sets
     * @param reps
     */
    public Exercise(String nombre, String sets, String reps) {
        this.nombre = nombre;
        this.sets = sets;
        this.reps = reps;
    }
}
