package org.example.dao;

import org.example.models.Cuadrilla;
import org.example.models.Olivar;

import java.util.List;

public interface OlivarDAO {
    public void add(Olivar ol);
    public void update(Olivar ol);
    public void delete(int id);
    public List<Olivar> read();
    public void asociarCuadOl(int id_Cuad,int id_Ol);
    List<Olivar> getOlivarByCuadId(int id);

}
