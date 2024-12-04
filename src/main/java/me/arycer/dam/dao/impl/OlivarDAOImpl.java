package me.arycer.dam.dao.impl;

import me.arycer.dam.dao.OlivarDAO;
import me.arycer.dam.database.DBConnection;
import me.arycer.dam.model.Olivar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OlivarDAOImpl implements OlivarDAO {
    private final Connection connection;

    public OlivarDAOImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void add(Olivar ol) {
        String sql = "insert into Olivar (ubicacion,hectareas,produccionAnual) values(?,?,?) ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, ol.getUbicacion());
            st.setDouble(2, ol.getHectareas());
            st.setDouble(3, ol.getProduccionAnual());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Olivar ol) {
        String sql = "update Olivar set ubicacion=?,hectareas=?,produccionAnual=? where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, ol.getUbicacion());
            st.setDouble(2, ol.getHectareas());
            st.setDouble(3, ol.getProduccionAnual());
            st.setInt(4, ol.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Olivar where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Olivar> read() {
        List<Olivar> lista = new ArrayList<>();
        String sql = "select * from Olivar";

        try (Statement st = connection.createStatement()) {
            CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Olivar ol = new Olivar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        cuadrillaDAOImpl.getCuadrillasByOlivarId(rs.getInt(1))
                );
                lista.add(ol);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void asociarCuadrillaOlivar(int id_cuadrilla, int id_olivar) {
        String sql = "insert into Cuadrilla_Olivar (cuadrilla_id,olivar_id) values (?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id_cuadrilla);
            st.setInt(2, id_olivar);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Olivar> getOlivarByCuadrillaId(int id) {
        List<Olivar> lista = new ArrayList<>();
        String sql = """
            select o.*
            from Cuadrilla c, Olivar o, Cuadrilla_Olivar rel
            where rel.cuadrilla_id=c.id
              and rel.olivar_id=o.id
              and c.id=?
            """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Olivar ol = new Olivar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        cuadrillaDAOImpl.getCuadrillasByOlivarId(rs.getInt(1))
                );
                lista.add(ol);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
