package me.arycer.dam.dao;

import me.arycer.dam.model.Olivar;

import java.util.List;

/**
 * Representa los métodos CRUD y auxiliares sobre la tabla Olivar
 */
public interface OlivarDAO {
    /**
     * Añade un olivar a la tabla
     * @param ol olivar a añadir
     */
    void add(Olivar ol);

    /**
     * Actualiza un olivar en la tabla
     * @param ol olivar a actualizar
     */
    void update(Olivar ol);

    /**
     * Elimina un olivar de la tabla
     * @param id id del olivar a eliminar
     */
    void delete(int id);

    /**
     * Lee todos los olivares de la tabla
     * @return lista de olivares
     */
    List<Olivar> read();

    /**
     * Asocia un olivar a una cuadrilla
     * @param id_cuadrilla id de la cuadrilla
     * @param id_olivar id del olivar
     */
    void asociarCuadrillaOlivar(int id_cuadrilla, int id_olivar);

    /**
     * Obtiene una lista de olivares donde trabaja una determinada cuadrilla
     * @param id id de la cuadrilla
     * @return lista de olivares
     */
    List<Olivar> getOlivarByCuadrillaId(int id);
}
