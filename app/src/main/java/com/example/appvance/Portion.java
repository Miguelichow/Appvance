package com.example.appvance;

/**
 * Clase que crea las porciones
 * @author Miguel Prieto Horcajo
 */
public class Portion {

    String time;
    int protein, fat, carbs;

    /**
     * Constructor de la clase
     * @param time Comida
     * @param protein Proteína
     * @param fat Grasas
     * @param carbs Carbohidratos
     */
    public Portion(String time, int protein, int fat, int carbs) {
        this.time = time;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    /**
     * Constructor simpel
     * @param protein Proteína
     * @param fat Grasas
     * @param carbs Carbohidratos
     */
    public Portion(int protein, int fat, int carbs) {
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}
