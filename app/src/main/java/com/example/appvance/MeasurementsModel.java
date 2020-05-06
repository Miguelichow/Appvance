package com.example.appvance;

/**
 * Crea el modelo de las medidas
 * @author Miguel Prieto Horcajo
 */
public class MeasurementsModel {

    private String fecha, tripa;
    private int imgWeight;

    /**
     * Constructor de la clase
     * @param fecha Fecha actual
     * @param tripa Medida
     * @param imgWeight Imagen a mostrar dependiendo de la variación de la medida
     */
    public MeasurementsModel(String fecha, String tripa, int imgWeight) {
        this.fecha = fecha;
        this.tripa = tripa;
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
     * @param fecha Nueva fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el peso
     * @return Peso
     */
    public String getPeso() {
        return tripa;
    }

    /**
     * Establece el peso
     * @param tripa Nuevo peso
     */
    public void setPeso(String tripa) {
        this.tripa = tripa;
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
