package com.example.appvance;

/**
 * Crea el modelo del peso
 * @author Miguel Prieto Horcajo
 */
public class WeightModel {

    private String fecha, peso;
    private int imgWeight;

    /**
     * Constructor de la clase
     * @param fecha Fecha
     * @param peso Peso
     * @param imgWeight Imagen del peso
     */
    public WeightModel(String fecha, String peso, int imgWeight) {
        this.fecha = fecha;
        this.peso = peso;
        this.imgWeight = imgWeight;
    }

    /**
     * Devuelve la fecha
     * @return Fecha actual
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha
     * @param fecha Fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el peso
     * @return Peso
     */
    public String getPeso() {
        return peso;
    }

    /**
     * Establece el peso
     * @param peso Peso
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    /**
     * Devuelve la imagen
     * @return Imagen
     */
    public int getImgWeight() {
        return imgWeight;
    }

    /**
     * Establece la imagen
     * @param imgWeight Imagen
     */
    public void setImgWeight(int imgWeight) {
        this.imgWeight = imgWeight;
    }
}
