package org.example.dao;

import org.example.models.Olivar;
import org.example.models.Trabajador;

import java.util.List;

public interface TrabajadorDAO {
    public void add(Trabajador t);
    public void update(Trabajador t);
    public void delete(int id);
    public List<Trabajador> read();
    public void asociarCuadTrab(int id_Cuad,int id_Trab);
    List<Trabajador> getTrabajadorByCuadId(int id);
}
