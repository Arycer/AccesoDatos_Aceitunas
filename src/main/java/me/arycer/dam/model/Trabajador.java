package org.example.models;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
@XmlRootElement(name = "Trabjadores")
public class Trabajador {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "edad")
    private int edad;
    @XmlElement(name = "puesto")
    private String puesto;
    @XmlElement(name = "salario")
    private double salario;
    private transient List<Cuadrilla> lista;

    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                ", lista=" + lista +
                '}';
    }

    public Trabajador() {
    }

    public Trabajador(int id, String nombre, int edad, String puesto, double salario, List<Cuadrilla> lista) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
        this.lista = lista;
    }

    public Trabajador(int id, String nombre, int edad, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Trabajador(String nombre, int edad, String puesto, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public List<Cuadrilla> getLista() {
        return lista;
    }
}
