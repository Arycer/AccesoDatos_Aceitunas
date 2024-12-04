package org.example.Contenedores;

import org.example.models.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Olivares")
public class Olivares {
    @XmlElement(name = "Olivar")
    private List<Olivar> lista;

    public Olivares() {
    }

    public Olivares(List<Olivar> lista) {
        this.lista = lista;
    }

    public List<Olivar> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return "Olivares{" +
                "lista=" + lista +
                '}';
    }
}
