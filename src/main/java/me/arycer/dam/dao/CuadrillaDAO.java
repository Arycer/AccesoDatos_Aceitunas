package me.arycer.dam.dao;

import me.arycer.dam.model.Cuadrilla;

import java.util.List;

/**
 * Representa los métodos CRUD y auxiliares sobre la tabla Cuadrilla
 */
public interface CuadrillaDAO {
    /**
     * Añade una cuadrilla a la tabla
     * @param cuadrilla cuadrilla a añadir
     */
    void add(Cuadrilla cuadrilla);

    /**
     * Lee todas las cuadrillas de la tabla
     * @return lista de cuadrillas
     */
    List<Cuadrilla> read();

    /**
     * Actualiza una cuadrilla en la tabla
     * @param cuadrilla cuadrilla a actualizar
     */
    void update(Cuadrilla cuadrilla);

    /**
     * Elimina una cuadrilla de la tabla
     * @param id id de la cuadrilla a eliminar
     */
    void delete(int id);

    /**
     * Obtiene una lista de cuadrillas que trabajan en un determinado olivar
     * @param id id del olivar
     * @return lista de cuadrillas
     */
    List<Cuadrilla> getCuadrillasByOlivarId(int id);

    /**
     * Obtiene una lista de cuadrillas supervisadas por un determinado trabajador
     * @param id id del trabajador
     * @return lista de cuadrillas
     */
    List<Cuadrilla> getCuadrillasByTrabajadorId(int id);

    /**
     * Obtiene una lista de cuadrillas supervisadas por un determinado supervisor
     * @param id id del supervisor
     * @return lista de cuadrillas
     */
    List<Cuadrilla> getCuadrillasBySupervisorId(int id);
}
