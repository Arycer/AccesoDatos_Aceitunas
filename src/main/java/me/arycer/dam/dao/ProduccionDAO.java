package me.arycer.dam.dao;

import me.arycer.dam.model.Almazara;
import me.arycer.dam.model.Cuadrilla;
import me.arycer.dam.model.Olivar;
import me.arycer.dam.model.Produccion;

import java.util.List;

/**
 * Representa los métodos CRUD y auxiliares sobre la tabla Produccion
 */
public interface ProduccionDAO {
    /**
     * Añade una producción a la tabla
     * @param p producción a añadir
     */
    void add(Produccion p);

    /**
     * Actualiza una producción en la tabla
     * @param p producción a actualizar
     */
    void update(Produccion p);

    /**
     * Elimina una producción de la tabla
     * @param id id de la producción a eliminar
     */
    void delete(int id);

    /**
     * Lee todas las producciones de la tabla
     * @return lista de producciones
     */
    List<Produccion> read();

    List<Olivar> getOlivarByCuadrillaId(int id);

    List<Cuadrilla> getCuadrillaByOlivarId(int id);

    List<Almazara> getAlmazaraByCuadrillaId(int id);

    Produccion getProduccionByIds(int almazara_id, String fecha, int cuadrilla_id);

    Produccion getProduccionByFechaAl(String fecha, int almazara_id);

    Produccion getProduccionByFechaOl(String fecha, int olivar_id);

    Produccion getProduccionByFechaCuadrilla(String fecha, int cuadrilla_id);
}
