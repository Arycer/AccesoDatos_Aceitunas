package org.example.models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@XmlRootElement(name = "Olivar")
public class Olivar {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "ubicacion")
    private String ubicacion;
    @XmlElement(name = "hectareas")
    private double hectareas;
    @XmlElement(name = "produccionAnual")
    private double produccionAnual;
    private transient List<Cuadrilla> cuadrillaList;

    @Override
    public String toString() {
        return "Olivar{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", hectareas=" + hectareas +
                ", produccionAnual=" + produccionAnual +
                ", cuadrillaList=" + cuadrillaList +
                '}';
    }

    public Olivar() {
    }

    public Olivar(int id, String ubicacion, double hectareas, double produccionAnual) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public Olivar(int id, String ubicacion, double hectareas, double produccionAnual, List<Cuadrilla> cuadrillaList) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
        this.cuadrillaList = cuadrillaList;
    }

    public Olivar(String ubicacion, double hectareas, double produccionAnual) {
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public void setCuadrillaList(List<Cuadrilla> cuadrillaList) {
        this.cuadrillaList = cuadrillaList;
    }

    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getHectareas() {
        return hectareas;
    }

    public double getProduccionAnual() {
        return produccionAnual;
    }
}
