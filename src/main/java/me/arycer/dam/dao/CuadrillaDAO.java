package org.example.dao;

import org.example.models.Almazara;
import org.example.models.Cuadrilla;

import java.util.List;

public interface CuadrillaDAO {

    void add(Cuadrilla cuad);
    List<Cuadrilla> read();
    List<Cuadrilla> getCuadrillasByOlivarId(int id);
    List<Cuadrilla> getCuadrillasByTrabajadorId(int id);
    void update(Cuadrilla cuad);
    void delete(int id);
    List<Cuadrilla> getCuadrillasBySupervisorId(int id);


}
