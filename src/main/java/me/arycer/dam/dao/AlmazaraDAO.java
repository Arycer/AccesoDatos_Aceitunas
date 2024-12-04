package org.example.dao;

import org.example.conexion.Conexion;
import org.example.models.Almazara;

import java.sql.Connection;
import java.util.List;

public interface AlmazaraDAO {
    void add(Almazara al);
    List<Almazara> read();
    void update(Almazara al);
    void delete(int id);
}
