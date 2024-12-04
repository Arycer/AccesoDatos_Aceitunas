package org.example.ClasesDAO;


import org.example.conexion.Conexion;
import org.example.models.Almazara;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlmazaraDAO implements org.example.dao.AlmazaraDAO {
    Connection c;

    public AlmazaraDAO() {
        this.c = Conexion.getConnection();
    }


    //Metodo para meter un dato a la base de datos
    @Override
    public void add(Almazara al) {
        String sql="insert into Almazara (nombre,ubicacion,capacidad) values(?,?,?)";
        try (PreparedStatement st = c.prepareStatement(sql)){

            st.setString(1,al.getNombre());
            st.setString(2,al.getUbicacion());
            st.setDouble(3,al.getCapacidad());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //Metodo para leer todos los de la base de datos
    @Override
    public List<Almazara> read() {

        List<Almazara> lista = new ArrayList<>();

        String sql="select * from Almazara";
        try (Statement st = c.createStatement()){

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
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
    //Metodo para actualizar una dato
    @Override
    public void update(Almazara al) {

        String sql = "update Almazara set nombre=?,ubicacion=?,capacidad=? where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1,al.getNombre());
            st.setString(2,al.getUbicacion());
            st.setDouble(3,al.getCapacidad());
            st.setInt(4,al.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //Metodo para borrar un dato
    @Override
    public void delete(int id) {

        String sql="delete from Almazara where id=?";

        try (PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
