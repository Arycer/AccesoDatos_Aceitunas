package me.arycer.dam.dao.impl;

import me.arycer.dam.dao.AlmazaraDAO;
import me.arycer.dam.database.DBConnection;
import me.arycer.dam.model.Almazara;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlmazaraDAOImpl implements AlmazaraDAO {
    private final Connection connection;

    public AlmazaraDAOImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void add(Almazara al) {
        String sql = "insert into Almazara (nombre,ubicacion,capacidad) values(?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, al.getNombre());
            st.setString(2, al.getUbicacion());
            st.setDouble(3, al.getCapacidad());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Almazara> read() {
        List<Almazara> lista = new ArrayList<>();

        String sql = "select * from Almazara";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Almazara al = new Almazara(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );
                lista.add(al);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public void update(Almazara al) {
        String sql = "update Almazara set nombre=?,ubicacion=?,capacidad=? where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, al.getNombre());
            st.setString(2, al.getUbicacion());
            st.setDouble(3, al.getCapacidad());
            st.setInt(4, al.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Almazara where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
