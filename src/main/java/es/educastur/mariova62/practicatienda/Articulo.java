/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.mariova62.practicatienda;

/**
 *
 * @author 1dawd07
 */
import java.io.Serializable;

public class Articulo implements Serializable {

    private String idArticulo;
    private String description;
    private int existencias;
    private double pvp;

    public Articulo(String idArticulo, String description, int existencias, double pvp) {
        this.idArticulo = idArticulo;
        this.description = description;
        this.existencias = existencias;
        this.pvp = pvp;
    }

    public String getArticulo() {
        return idArticulo;
    }

    public void setArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    /*@Override
    public String toString() {
        return "Articulo [idArticulo=" + idArticulo + ", description=" + description + ", existencias=" + existencias
                + ", pvp=" + pvp + "]";
    }*/
    @Override
    public String toString() {
        return String.format("""
        
        \tARTÍCULO
        ───────────────────────
        ID:          %s
        Descripción: %s
        Stock:       %d unidades
        Precio:      %.2f
        """,
                idArticulo, description, existencias, pvp
        );
    }

}
