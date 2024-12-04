package org.example.Contenedores;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Base_De_Datos")
public class BaseDeDatos {
    @XmlElement(name = "Almazaras")
    private Almazaras almazaras;
    @XmlElement(name = "Cuadrillas")
    private Cuadrillas cuadrillas;
    @XmlElement(name = "Olivares")
    private Olivares olivares;
    @XmlElement(name = "Producciones")
    private Producciones producciones;
    @XmlElement(name = "Tranajadores")
    private Trabajadores trabajadores;

    public BaseDeDatos(Almazaras almazaras, Cuadrillas cuadrillas, Olivares olivares, Producciones producciones, Trabajadores trabajadores) {
        this.almazaras = almazaras;
        this.cuadrillas = cuadrillas;
        this.olivares = olivares;
        this.producciones = producciones;
        this.trabajadores = trabajadores;
    }

    public Almazaras getAlmazaras() {
        return almazaras;
    }

    public Cuadrillas getCuadrillas() {
        return cuadrillas;
    }

    public Olivares getOlivares() {
        return olivares;
    }

    public Producciones getProducciones() {
        return producciones;
    }

    public Trabajadores getTrabajadores() {
        return trabajadores;
    }

    public BaseDeDatos() {
    }

    @Override
    public String toString() {
        return "BaseDeDatos{" +
                "almazaras=" + almazaras +
                ", cuadrillas=" + cuadrillas +
                ", olivares=" + olivares +
                ", producciones=" + producciones +
                ", trabajadores=" + trabajadores +
                '}';
    }
}
