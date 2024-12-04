package me.arycer.dam.dao.impl;

import org.example.conexion.Conexion;
import org.example.models.Olivar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OlivarDAO implements org.example.dao.OlivarDAO {
    Connection c;

    public OlivarDAO() {
        this.c = Conexion.getConnection();
    }
    //Metodo para meter un dato a la base de datos
    @Override
    public void add(Olivar ol) {
        String sql="insert into Olivar (ubicacion,hectareas,produccionAnual) values(?,?,?) ";
        try (PreparedStatement st = c.prepareStatement(sql)){

            st.setString(1,ol.getUbicacion());
            st.setDouble(2,ol.getHectareas());
            st.setDouble(3,ol.getProduccionAnual());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para actualizar una dato
    @Override
    public void update(Olivar ol) {
        String sql = "update Olivar set ubicacion=?,hectareas=?,produccionAnual=? where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1,ol.getUbicacion());
            st.setDouble(2,ol.getHectareas());
            st.setDouble(3,ol.getProduccionAnual());
            st.setInt(4,ol.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para borrar un dato
    @Override
    public void delete(int id) {
        String sql="delete from Olivar where id=?";

        try (PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para leer todos los de la base de datos
    @Override
    public List<Olivar> read() {
        List<Olivar> lista = new ArrayList<>();
        String sql ="select * from Olivar";

            try(Statement st = c.createStatement()){
                CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
                ResultSet rs =st.executeQuery(sql);

                while (rs.next()){
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
    public void asociarCuadOl(int id_Cuad, int id_Ol) {
        String sql="insert into Cuadrilla_Olivar (cuadrilla_id,olivar_id) values (?,?)";

        try (PreparedStatement st = c.prepareStatement(sql)){
            st.setInt(1,id_Cuad);
            st.setInt(2,id_Ol);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public List<Olivar> getOlivarByCuadId(int id) {
        List<Olivar> lista = new ArrayList<>();
        String sql="select ol.* from Cuadrilla cuad,Olivar ol, Cuadrilla_Olivar cuadol where cuadol.cuadrilla_id=cuad.id and cuadol.olivar_id=ol.id and cuad.id=?";
        try(PreparedStatement st = c.prepareStatement(sql)){
            CuadrillaDAOImpl cuadrillaDAOImpl = new CuadrillaDAOImpl();
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            while (rs.next()){
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
        return  lista;
    }
}
