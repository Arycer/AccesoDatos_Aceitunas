package org.example.Contenedores;

import org.example.models.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Producciones")
public class Producciones {
    @XmlElement(name = "Produccion")
    private List<Produccion> lista;

    public Producciones() {
    }

    public Producciones(List<Produccion> lista) {
        this.lista = lista;
    }

    public List<Produccion> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return "Producciones{" +
                "lista=" + lista +
                '}';
    }
}
