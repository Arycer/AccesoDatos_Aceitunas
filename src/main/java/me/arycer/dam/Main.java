package me.arycer.dam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.arycer.dam.container.*;
import me.arycer.dam.dao.*;
import me.arycer.dam.dao.impl.*;
import me.arycer.dam.model.Almazara;
import me.arycer.dam.model.Cuadrilla;
import me.arycer.dam.model.Olivar;
import me.arycer.dam.model.Trabajador;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlmazaraDAO almazaraDAO = new AlmazaraDAOImpl();
        OlivarDAO olivarDAO = new OlivarDAOImpl();
        TrabajadorDAO trabajadorDAO = new TrabajadorDAOImpl();
        CuadrillaDAO cuadrillaDAO = new CuadrillaDAOImpl();
        ProduccionDAO produccionDAO = new ProduccionDAOImpl();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--------------------");
            System.out.println("Menú de opciones:");
            System.out.println("1. Mostrar los trabajadores de una determinada cuadrilla.");
            System.out.println("2. Mostrar las cuadrillas que supervisa un determinado trabajador.");
            System.out.println("3. Mostrar los olivares donde trabaja una determinada cuadrilla.");
            System.out.println("4. Mostrar las cuadrillas que trabajan en un determinado olivar.");
            System.out.println("5. Mostrar las almazaras donde lleva aceituna una determinada cuadrilla.");
            System.out.println("6. Mostrar la producción en una fecha concreta, de una cuadrilla concreta en una almazara concreta.");
            System.out.println("7. Mostrar la producción hasta una determinada fecha, de una determinada almazara.");
            System.out.println("8. Mostrar la producción hasta una determinada fecha, de un determinado olivar.");
            System.out.println("9. Mostrar la producción hasta una determinada fecha, de una cuadrilla determinada.");
            System.out.println("10. Hacer XML de toda la Base De Datos.");
            System.out.println("11. Hacer JSON de toda la Base De Datos.");
            System.out.println("12. Leer XML.");
            System.out.println("13. Leer JSON.");
            System.out.println("0. Salir.");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Dime el id de la Cuadrilla que quieres ver");
                    int c = sc.nextInt();
                    for (Trabajador t : trabajadorDAO.getTrabajadorByCuadrillaId(c)) {
                        System.out.println(t.toString());
                    }
                }
                case 2 -> {
                    System.out.println("Dime el id del Supervisor que quieres ver");
                    int c = sc.nextInt();
                    for (Cuadrilla cuad : cuadrillaDAO.getCuadrillasBySupervisorId(c)) {
                        System.out.println(cuad.toString());
                    }
                }
                case 3 -> {
                    System.out.println("Dime el id de la Cuadrilla");
                    int c = sc.nextInt();
                    for (Olivar ol : produccionDAO.getOlivarByCuadrillaId(c)) {
                        System.out.println(ol.toString());
                    }
                }
                case 4 -> {
                    System.out.println("Dime el id de Olivar");
                    int c = sc.nextInt();
                    for (Cuadrilla cuad : produccionDAO.getCuadrillaByOlivarId(c)) {
                        System.out.println(cuad.toString());
                    }
                }
                case 5 -> {
                    System.out.println("Dime el id de la Cuadrilla para Obtener las Almazaras");
                    int c = sc.nextInt();
                    for (Almazara al : produccionDAO.getAlmazaraByCuadrillaId(c)) {
                        System.out.println(al.toString());
                    }
                }
                case 6 -> {
                    System.out.println("Dime la fecha que quieres Introducir");
                    String fecha = sc.next();
                    System.out.println("Dime el Id de la Almazara");
                    int c = sc.nextInt();
                    System.out.println("Dime el Id de la cuadrilla");
                    int d = sc.nextInt();
                    System.out.println(produccionDAO.getProduccionByIds(c, fecha, d).toString());
                }
                case 7 -> {
                    System.out.println("Dime la fecha que quieres Introducir");
                    String fecha1 = sc.next();
                    System.out.println("Dime el Id de la Almazara");
                    int c = sc.nextInt();
                    System.out.println(produccionDAO.getProduccionByFechaAl(fecha1, c));
                }
                case 8 -> {
                    System.out.println("Dime la fecha que quieres Introducir");
                    String fecha2 = sc.next();
                    System.out.println("Dime el Id del Olivar");
                    int c = sc.nextInt();
                    System.out.println(produccionDAO.getProduccionByFechaOl(fecha2, c));
                }
                case 9 -> {
                    System.out.println("Dime la fecha que quieres Introducir");
                    String fecha3 = sc.next();
                    System.out.println("Dime el Id de la Cuadrilla");
                    int c = sc.nextInt();
                    System.out.println(produccionDAO.getProduccionByFechaCuadrilla(fecha3, c));
                }
                case 10 -> {
                    Almazaras almazaras = new Almazaras(almazaraDAO.read());
                    Cuadrillas cuadrillas = new Cuadrillas(cuadrillaDAO.read());
                    Olivares olivares = new Olivares(olivarDAO.read());
                    Producciones producciones = new Producciones(produccionDAO.read());
                    Trabajadores trabajadores = new Trabajadores(trabajadorDAO.read());

                    BaseDeDatos baseDeDatos = new BaseDeDatos(almazaras, cuadrillas, olivares, producciones, trabajadores);
                    hacerXMLbaseDeDatos(baseDeDatos);
                }
                case 11 -> {
                    Almazaras almazaras = new Almazaras(almazaraDAO.read());
                    Cuadrillas cuadrillas = new Cuadrillas(cuadrillaDAO.read());
                    Olivares olivares = new Olivares(olivarDAO.read());
                    Producciones producciones = new Producciones(produccionDAO.read());
                    Trabajadores trabajadores = new Trabajadores(trabajadorDAO.read());

                    BaseDeDatos baseDeDatos = new BaseDeDatos(almazaras, cuadrillas, olivares, producciones, trabajadores);

                    hacerJSON(baseDeDatos);
                }
                case 12 -> leerXMLbaseDeDatos();
                case 13 -> leerJSON();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida, por favor intenta de nuevo.");
            }

            System.out.println("--------------------");
        } while (opcion != 0);
    }

    private static void hacerJSON(BaseDeDatos baseDeDatos) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(baseDeDatos);

        File f = new File("Archivos/JSON/BaseDeDatos.json");
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {

            bw.write(json);

            System.out.println("JSON hecho");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hacerXMLbaseDeDatos(BaseDeDatos baseDeDatos) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BaseDeDatos.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            File file = new File("Archivos/XML/BaseDeDatos.xml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            marshaller.marshal(baseDeDatos, file);

            System.out.println("XML de la Base De Datos Hecho");

        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leerXMLbaseDeDatos() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BaseDeDatos.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File file = new File("Archivos/XML/BaseDeDatos.xml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BaseDeDatos baseDeDatos1 = (BaseDeDatos) unmarshaller.unmarshal(file);

            System.out.println("Xml leido");

            System.out.println(baseDeDatos1.toString());

        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerJSON() {
        File f = new File("Archivos/JSON/BaseDeDatos.json");
        if (!f.exists()) {
            System.out.println("No existe el archivo JSON");
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            BaseDeDatos baseDeDatos = gson.fromJson(new FileReader(f), BaseDeDatos.class);

            System.out.println(baseDeDatos.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}