package com.example.appvance;

public class ExercisesRecycler {
    private String nombre;
    private int img;

    public ExercisesRecycler(String nombre, int img) {
        this.nombre = nombre;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
