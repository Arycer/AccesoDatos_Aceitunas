package me.arycer.dam.dao.impl;

import me.arycer.dam.dao.CuadrillaDAO;
import me.arycer.dam.database.DBConnection;
import me.arycer.dam.model.Cuadrilla;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAOImpl implements CuadrillaDAO {
    private final Connection connection;

    public CuadrillaDAOImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void add(Cuadrilla cuadrilla) {
        String sql = "insert into Cuadrilla (nombre,supervisor_id) values(?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, cuadrilla.getNombre());
            st.setInt(2, cuadrilla.getSupervisorId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cuadrilla> read() {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql = "select * from Cuadrilla";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Cuadrilla cuadrilla = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                lista.add(cuadrilla);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public List<Cuadrilla> getCuadrillasByOlivarId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql = """
                select c.*
                from Cuadrilla c,Olivar o, Cuadrilla_Olivar rel
                where rel.cuadrilla_id = c.id
                  and rel.olivar_id = o.id
                  and o.id = ?
                """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cuadrilla cuadrilla = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                lista.add(cuadrilla);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public List<Cuadrilla> getCuadrillasByTrabajadorId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql = """
            select c.*
            from Cuadrilla c, Trabajador t, Cuadrilla_Trabajador rel
            where rel.cuadrilla_id = c.id
              and rel.trabajador_id = t.id
              and t.id = ?
            """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cuadrilla cuad = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                lista.add(cuad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public void update(Cuadrilla cuadrilla) {
        String sql = """
            UPDATE Cuadrilla SET nombre = ?, supervisor_id = ? WHERE id = ?
            """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, cuadrilla.getNombre());
            st.setInt(2, cuadrilla.getSupervisorId());
            st.setInt(3, cuadrilla.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Cuadrilla where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cuadrilla> getCuadrillasBySupervisorId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql = "select * from Cuadrilla where supervisor_id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cuadrilla cuadrilla = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                lista.add(cuadrilla);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
