package org.example.Contenedores;

import org.apache.activemq.broker.region.policy.LastImageSubscriptionRecoveryPolicy;
import org.example.models.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "Trabajadores")
public class Trabajadores {
    @XmlElement(name = "Trabajador")
    private List<Trabajador> lista;

    public Trabajadores() {
    }

    public Trabajadores(List<Trabajador> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Trabajadores{" +
                "lista=" + lista +
                '}';
    }
}
