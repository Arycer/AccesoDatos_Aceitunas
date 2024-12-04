package org.example.models;

import org.example.conexion.Conexion;
import org.example.dao.AlmazaraDAO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Connection;
import java.util.List;
@XmlRootElement (name = "Almazara")
public class Almazara {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "ubicacion")
    private String ubicacion;
    @XmlElement(name = "capaciad")
    private double capacidad;

    public Almazara() {
    }

    @Override
    public String toString() {
        return "Almazara{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }

    public Almazara(int id, String nombre, String ubicacion, double capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public Almazara(String nombre, String ubicacion, double capacidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getCapacidad() {
        return capacidad;
    }
}
