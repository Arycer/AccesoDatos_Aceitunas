package me.arycer.dam.dao;

import me.arycer.dam.model.Trabajador;

import java.util.List;

public interface TrabajadorDAO {
    void add(Trabajador t);

    void update(Trabajador t);

    void delete(int id);

    List<Trabajador> read();

    void asociarCuadrillaTrabajador(int id_cuadrilla, int id_trabajador);

    List<Trabajador> getTrabajadorByCuadrillaId(int id);
}
