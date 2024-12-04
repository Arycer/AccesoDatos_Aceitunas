package org.example.Contenedores;

import org.example.models.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "Cuadrillas")
public class Cuadrillas {
    @XmlElement(name = "Cuadrilla")
    private List<Cuadrilla> lista;
    public Cuadrillas() {
    }

    public Cuadrillas(List<Cuadrilla> lista) {
        this.lista = lista;
    }

    public List<Cuadrilla> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return "Cuadrillas{" +
                "lista=" + lista +
                '}';
    }
}
