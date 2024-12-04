package me.arycer.dam.dao;

import me.arycer.dam.model.Almazara;

import java.util.List;

/**
 * Representa los métodos CRUD sobre la tabla Almazara
 */
public interface AlmazaraDAO {
    /**
     * Añade una almazara
     * @param al almazara a añadir
     */
    void add(Almazara al);

    /**
     * Lee todas las almazaras de la tabla
     * @return lista de almazaras
     */
    List<Almazara> read();

    /**
     * Actualiza una almazara en la tabla
     * @param al almazara a actualizar
     */
    void update(Almazara al);

    /**
     * Elimina una almazara de la tabla
     * @param id id de la almazara a eliminar
     */
    void delete(int id);
}
