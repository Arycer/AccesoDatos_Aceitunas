package me.arycer.dam.dao.impl;

import org.example.conexion.Conexion;
import org.example.models.Almazara;
import org.example.models.Cuadrilla;
import org.example.models.Olivar;
import org.example.models.Produccion;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProduccionDAO implements org.example.dao.ProduccionDAO {
    Connection c = Conexion.getConnection();
    DateTimeFormatter t = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Metodo para meter un dato a la base de datos
    @Override
    public void add(Produccion p) {
        String sql = "insert into Produccion(cuadrilla_id,olivar_id,almazara_id,fecha,cantidadRecolectada) values(?,?,?,?,?)";
        try(PreparedStatement st = c.prepareStatement(sql)){


            st.setInt(1,p.getCuadrilla_id());
            st.setInt(2,p.getOlivar_id());
            st.setInt(3,p.getAlmazara_id());
            st.setDate(4, Date.valueOf(LocalDate.parse(p.getFecha(),t)));
            st.setDouble(5,p.getCantidadRecolectada());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para actualizar una dato
    @Override
    public void update(Produccion p) {
        String sql="update Produccion cuadrilla_id=?,olivar_id=?,almazada_id=?,fecha=?,cantidadRecolectada=? where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,p.getCuadrilla_id());
            st.setInt(2,p.getOlivar_id());
            st.setInt(3,p.getAlmazara_id());
            st.setDate(4, Date.valueOf(LocalDate.parse(p.getFecha(),t)));
            st.setDouble(5,p.getCantidadRecolectada());
            st.setInt(6,p.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para borrar un dato
    @Override
    public void delete(int id) {
        String sql="delete from Produccion where id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodo para leer todos los de la base de datos
    @Override
    public List<Produccion> read() {
        List<Produccion> lista= new ArrayList<>();
        String sql="select * from Produccion";
        try(Statement st = c.createStatement()){

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){

                Produccion p = new Produccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(1)
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  lista;
    }

    //Metodos especificos para hace el ejercicio

    public List<Olivar> PgetOlivarByCuadrillaId(int id){
        List<Olivar> lista = new ArrayList<>();

        String sql="select ol.* from Olivar ol, Produccion p where ol.id=p.olivar_id and p.cuadrilla_id=?";

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

        return lista;
    }

    @Override
    public List<Cuadrilla> PgetCuadrillaByolivarId(int id) {
        List<Cuadrilla> lista = new ArrayList<>();

        String sql="select cuad.* from Cuadrilla cuad, Produccion p where cuad.id=p.cuadrilla_id and p.cuadrilla_id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
            OlivarDAOImpl olivarDAOImpl = new OlivarDAOImpl();
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Cuadrilla cuad = new Cuadrilla(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        trabajadorDAO.getTrabajadorByCuadId(rs.getInt(1)),
                        olivarDAOImpl.getOlivarByCuadId(rs.getInt(1))
                );
                lista.add(cuad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public List<Almazara> PgetAlmazaaraByCuadrillaId(int id) {
        List<Almazara> lista = new ArrayList<>();

        String sql="select al.* from Almazara al, Produccion p where al.id=p.almazara_id and p.cuadrilla_id=?";

        try(PreparedStatement st = c.prepareStatement(sql)){
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            while (rs.next()){
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
    public Produccion getProdruccionByIds(int almazara_id, String fecha, int cuadrilla_id) {
        String sql="SELECT p.* FROM Produccion p WHERE p.cuadrilla_id = ? AND p.almazara_id = ? AND p.fecha = ?;";
        Produccion p=null;
        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,cuadrilla_id);
            st.setInt(2,almazara_id);
            st.setDate(3,Date.valueOf(fecha));
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                p = new Produccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getDouble(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Produccion getProduccionByFechaAl(String fecha, int almazara_id) {
        String sql="SELECT p.* FROM Produccion p WHERE p.almazara_id = ? AND p.fecha=?";
        Produccion p=null;
        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,almazara_id);
            st.setDate(2,Date.valueOf(fecha));
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                p = new Produccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getDouble(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Produccion getProduccionByFechaOl(String fecha, int olivar_id) {
        String sql="SELECT p.* FROM Produccion p WHERE p.olivar_id = ? AND p.fecha=?";
        Produccion p=null;
        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,olivar_id);
            st.setDate(2,Date.valueOf(fecha));
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                p = new Produccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getDouble(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Produccion getProduccionByFechaCuad(String fecha, int cuadrilla_id) {
        String sql="SELECT p.* FROM Produccion p WHERE p.cuadrilla_id = ? AND p.fecha=?";
        Produccion p=null;
        try(PreparedStatement st = c.prepareStatement(sql)){

            st.setInt(1,cuadrilla_id);
            st.setDate(2,Date.valueOf(fecha));
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                p = new Produccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getDouble(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }


}
