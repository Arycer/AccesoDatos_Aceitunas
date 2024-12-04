package org.example.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@XmlRootElement(name = "Cuadrilla")
public class Cuadrilla {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "supervisorId")
    private int supervisorId;
    private transient List<Trabajador> trabajadorList;
    private transient List<Olivar> olivarList;

    public Cuadrilla() {
    }

    @Override
    public String toString() {
        return "Cuadrilla{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", supervisorId=" + supervisorId +
                '}';
    }

    public Cuadrilla(int id, String nombre, int supervisorId) {
        this.id = id;
        this.nombre = nombre;
        this.supervisorId = supervisorId;
    }

    public Cuadrilla(int id, String nombre, int supervisorId, List<Trabajador> trabajadorList, List<Olivar> olivarList) {
        this.id = id;
        this.nombre = nombre;
        this.supervisorId = supervisorId;
        this.trabajadorList = trabajadorList;
        this.olivarList = olivarList;
    }

    public Cuadrilla(String nombre, int supervisorId) {
        this.nombre = nombre;
        this.supervisorId = supervisorId;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public List<Olivar> getOlivarList() {
        return olivarList;
    }
}
