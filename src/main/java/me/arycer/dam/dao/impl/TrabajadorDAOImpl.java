package me.arycer.dam.dao.impl;

import me.arycer.dam.dao.TrabajadorDAO;
import me.arycer.dam.database.DBConnection;
import me.arycer.dam.model.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAOImpl implements TrabajadorDAO {
    private final Connection connection;

    public TrabajadorDAOImpl() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void add(Trabajador t) {
        String sql = "insert into Trabajador (nombre,edad,puesto,salario) values(?,?,?,?) ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getNombre());
            st.setInt(2, t.getId());
            st.setString(3, t.getPuesto());
            st.setDouble(4, t.getSalario());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trabajador t) {
        String sql = "update Trabajador set nombre=?,edad=?,puesto=?,salario=? where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getNombre());
            st.setInt(2, t.getEdad());
            st.setString(3, t.getPuesto());
            st.setDouble(4, t.getSalario());
            st.setInt(5, t.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para borrar un dato
    @Override
    public void delete(int id) {
        String sql = "delete from Trabajador where id=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para leer todos los de la base de datos
    @Override
    public List<Trabajador> read() {
        List<Trabajador> lista = new ArrayList<>();

        String sql = "select * from Trabajador";

        try (Statement st = connection.createStatement()) {
            CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Trabajador t = new Trabajador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        cuadrillaDAOImpl.getCuadrillasByTrabajadorId(rs.getInt(1))
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public void asociarCuadrillaTrabajador(int id_cuadrilla, int id_trabajador) {
        String sql = "insert into Cuadrilla_Trabajador (cuadrilla_id,trabajador_id) values (?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id_cuadrilla);
            st.setInt(2, id_trabajador);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trabajador> getTrabajadorByCuadrillaId(int id) {
        List<Trabajador> lista = new ArrayList<>();
        String sql = """
            select t.*
            from Cuadrilla c,Trabajador t, Cuadrilla_Trabajador rel
            where rel.cuadrilla_id=c.id
              and rel.trabajador_id=t.id
              and c.id=?
            """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Trabajador t = new Trabajador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        cuadrillaDAOImpl.getCuadrillasByTrabajadorId(rs.getInt(1))
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
