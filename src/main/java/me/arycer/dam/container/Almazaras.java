package me.arycer.dam.container;


import me.arycer.dam.model.Almazara;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Almazaras")
public class Almazaras {
    @XmlElement(name = "Almazara")
    private List<Almazara> lista;

    public Almazaras(List<Almazara> lista) {
        this.lista = lista;
    }

    public Almazaras() {
    }

    @Override
    public String toString() {
        return "Almazaras{" +
                "lista=" + lista +
                '}';
    }

    public List<Almazara> getLista() {
        return lista;
    }
}
