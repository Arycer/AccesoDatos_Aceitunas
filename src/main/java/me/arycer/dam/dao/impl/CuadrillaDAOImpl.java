package me.arycer.dam.dao.impl;

import org.example.conexion.Conexion;
import org.example.models.Cuadrilla;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO implements org.example.dao.CuadrillaDAO {

    Connection c;

    public CuadrillaDAO() {
        this.c  = Conexion.getConnection();
    }
    //Metodo para meter un dato a la base de datos
    @Override
    public void add(Cuadrilla cuad) {
        String sql="insert into Cuadrilla (nombre,supervisor_id) values(?,?)";

        try(PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1, cuad.getNombre());
            st.setInt(2,cuad.getSupervisorId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para leer todos los de la base de datos
    @Override
    public List<Cuadrilla> read() {
        List<Cuadrilla> lista= new ArrayList<>();
        String sql="select * from Cuadrilla";
        try(Statement st = c.createStatement()){
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
            OlivarDAO olivarDAO = new OlivarDAO();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){

                Cuadrilla cuad = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)/*,
                        trabajadorDAO.getTrabajadorByCuadId(rs.getInt(1)),
                        olivarDAO.getOlivarByCuadId(rs.getInt(1))*/
                );
                lista.add(cuad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  lista;
    }

    @Override
    public List<Cuadrilla> getCuadrillasByOlivarId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql="select cuad.* from Cuadrilla cuad,Olivar ol, Cuadrilla_Olivar cuadol where cuadol.cuadrilla_id=cuad.id and cuadol.olivar_id=ol.id and ol.id=?";
            try(PreparedStatement st = c.prepareStatement(sql)){
                TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
                OlivarDAO olivarDAO = new OlivarDAO();
                st.setInt(1,id);

                ResultSet rs = st.executeQuery();

                while (rs.next()){
                    Cuadrilla cuad = new Cuadrilla(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3)/*,
                            trabajadorDAO.getTrabajadorByCuadId(rs.getInt(1)),
                            olivarDAO.getOlivarByCuadId(rs.getInt(1))*/
                    );
                    lista.add(cuad);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return  lista;
    }

    @Override
    public List<Cuadrilla> getCuadrillasByTrabajadorId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();
        String sql="select cuad.* from Cuadrilla cuad,Trabajador t, Cuadrilla_Trabajador cuadt where cuadt.cuadrilla_id=cuad.id and cuadt.trabajador_id=t.id and t.id=?";

        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        OlivarDAO olivarDAO = new OlivarDAO();

        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Cuadrilla cuad = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                        /*trabajadorDAO.getTrabajadorByCuadId(rs.getInt(1)),
                        olivarDAO.getOlivarByCuadId(rs.getInt(1))*/
                );
                lista.add(cuad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  lista;
    }
    //Metodo para actualizar una dato
    @Override
    public void update(Cuadrilla cuad) {

        String sql="update Cuadrilla nombe=?,supervisor_id=? where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setString(1, cuad.getNombre());
            st.setInt(2,cuad.getSupervisorId());
            st.setInt(3,cuad.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //Metodo para borrar un dato
    @Override
    public void delete(int id) {

        String sql="delete Cuadrilla where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cuadrilla> getCuadrillasBySupervisorId(int id){
        List<Cuadrilla> lista= new ArrayList<>();
        String sql="select * from Cuadrilla where supervisor_id=?";
        try(PreparedStatement st = c.prepareStatement(sql)){
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
            OlivarDAO olivarDAO = new OlivarDAO();
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            while (rs.next()){

                Cuadrilla cuad = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)/*,
                        trabajadorDAO.getTrabajadorByCuadId(rs.getInt(1)),
                        olivarDAO.getOlivarByCuadId(rs.getInt(1))*/
                );
                lista.add(cuad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  lista;
    }
}
