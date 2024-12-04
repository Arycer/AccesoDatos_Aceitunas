package org.example.dao;

import org.example.models.Almazara;
import org.example.models.Cuadrilla;
import org.example.models.Olivar;
import org.example.models.Produccion;

import java.util.List;
import java.util.Properties;

public interface ProduccionDAO {
    public void add(Produccion p);
    public void update(Produccion p);
    public void delete(int id);
    public List<Produccion> read();
    List<Olivar> PgetOlivarByCuadrillaId(int id);
    List<Cuadrilla> PgetCuadrillaByolivarId(int id);
    List<Almazara> PgetAlmazaaraByCuadrillaId(int id);
    Produccion getProdruccionByIds(int almazara_id,String fecha,int cuadrilla_id);
    Produccion getProduccionByFechaAl(String fecha,int almazara_id);
    Produccion getProduccionByFechaOl(String fecha,int olivar_id);
    Produccion getProduccionByFechaCuad(String fecha,int cuadrilla_id);
}
