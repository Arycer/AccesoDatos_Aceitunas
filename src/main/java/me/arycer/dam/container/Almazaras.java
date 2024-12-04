package org.example.Contenedores;


import org.example.models.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement (name = "Almazaras")
public class Almazaras {
    @XmlElement(name="Almazara")
    private List<Almazara> lista;

    @Override
    public String toString() {
        return "Almazaras{" +
                "lista=" + lista +
                '}';
    }

    public Almazaras(List<Almazara> lista) {
        this.lista = lista;
    }

    public Almazaras() {
    }

    public List<Almazara> getLista() {
        return lista;
    }
}
