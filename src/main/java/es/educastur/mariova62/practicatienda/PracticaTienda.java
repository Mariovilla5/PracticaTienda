/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package es.educastur.mariova62.practicatienda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * PracticaTienda
 *
 * @author Adrián Cuervo - CreidenCR99
 * @version 12/03/26
 */
public class PracticaTienda implements Serializable {

    private static final transient Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;
    LocalDate hoy = LocalDate.now();

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(HashMap<String, Articulo> articulos) {
        this.articulos = articulos;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    private static final String[] nombreSecciones = {
        "1 - Perifericos",
        "2 - Almacenamiento",
        "3 - Impresoras",
        "4 - Monitores"
    };

    public PracticaTienda() {
        pedidos = new ArrayList<>();
        articulos = new HashMap<>();
        clientes = new HashMap<>();
    }

    public static void main(String[] args) {
        PracticaTienda t = new PracticaTienda();
        //t.cargaDatos();
        PracticaTienda tImportada = t.importarTiendaCompleta();
        if (tImportada != null) {
            t = tImportada;
        }
        t.menuOpciones();
        System.out.println("\n-\tGuardando...");
        t.backupTiendaCompleta(t);
        t.backupColecciones();
        t.backupArticulosPorSeccion();
        System.out.println("\n-\tFin del programa");
    }

    //#region Backup
    public void cargaDatos() {
        clientes.put("80580845T", new Cliente("80580845T", "ANA", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));

        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST", 0, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD", 5, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB", 15, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-11", new Articulo("3-11", "HP LASERJET HP800", 2, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));

        pedidos.add(new Pedido("80580845T-001/2025", clientes.get("80580845T"), hoy.minusDays(1), new ArrayList<>(List.of(new LineaPedido(articulos.get("1-11"), 3), new LineaPedido(articulos.get("4-22"), 3)))));
        pedidos.add(new Pedido("80580845T-002/2025", clientes.get("80580845T"), hoy.minusDays(2), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-11"), 3), new LineaPedido(articulos.get("4-22"), 2), new LineaPedido(articulos.get("4-33"), 4)))));
        pedidos.add(new Pedido("36347775R-001/2025", clientes.get("36347775R"), hoy.minusDays(3), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-22"), 1), new LineaPedido(articulos.get("2-22"), 3)))));
        pedidos.add(new Pedido("36347775R-002/2025", clientes.get("36347775R"), hoy.minusDays(5), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-33"), 3), new LineaPedido(articulos.get("2-11"), 3)))));
        pedidos.add(new Pedido("63921307Y-001/2025", clientes.get("63921307Y"), hoy.minusDays(4), new ArrayList<>(List.of(new LineaPedido(articulos.get("2-11"), 5), new LineaPedido(articulos.get("2-33"), 3), new LineaPedido(articulos.get("4-33"), 2)))));
    }

    private PracticaTienda importarTiendaCompleta() {
        PracticaTienda t = null;
        try (ObjectInputStream oisPracticaTienda = new ObjectInputStream(new FileInputStream("archivos/PracticaTienda.dat"))) {
            t = (PracticaTienda) oisPracticaTienda.readObject();
            System.out.println("Tienda importada correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PracticaTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    private void backupTiendaCompleta(PracticaTienda t) {
        try (ObjectOutputStream oosPracticaTienda = new ObjectOutputStream(new FileOutputStream("archivos/PracticaTienda.dat"))) {
            oosPracticaTienda.writeObject(t);
            System.out.println("Tienda guardada correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void backupColecciones() {
        try (ObjectOutputStream oosArticulos = new ObjectOutputStream(new FileOutputStream("archivos/articulos/Articulos.dat")); // Comnetario 1
                 ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("archivos/clientes/Clientes.dat")); // Comentario 2
                 ObjectOutputStream oosPedidos = new ObjectOutputStream(new FileOutputStream("archivos/pedidos/Pedidos.dat"))) {

            for (Articulo a : articulos.values()) {
                oosArticulos.writeObject(a);
            }
            for (Cliente c : clientes.values()) {
                oosClientes.writeObject(c);
            }
            for (Pedido p : pedidos) {
                oosPedidos.writeObject(p);
            }
            System.out.println("Colecciones guardadas correctamente");
        } catch (IOException ex) {
            System.out.println("No se han podido crear los archivos correctamente, " + "revise unidades de almacenamiento e inténtelo de nuevo");
            File f = new File("archivos/articulos/articulos.dat");
            f.delete();
            f = new File("archivos/clientes/clientes.dat");
            f.delete();
            f = new File("archivos/pedidos/pedidos.dat");
            f.delete();
        }
    }

    private void importarColecciones() {
        /* HAY QUE LEER DESDE CADA ARCHIVO POR SEPARADO PORQUE SI INTENTAMOS METERLO TODO EN EL MISMO
        TRY-CATCH AL LLEGAR AL FINAL DEL PRIMER ARCHIVO SE PRODUCE LA EOFException Y SÓLO SE 
        LEERÍA BIEN EL PRIMER ARCHIVO, EL RESTO NO */

        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("archivos/articulos/articulos.dat"))) {
            Articulo a;
            while ((a = (Articulo) oisArticulos.readObject()) != null) {
                articulos.put(a.getArticulo(), a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo articulos.dat");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("archivos/clientes/clientes.dat"))) {
            Cliente c;
            while ((c = (Cliente) oisClientes.readObject()) != null) {
                clientes.put(c.getIdCliente(), c);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo clientes.dat");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("archivos/pedidos/pedidos.dat"))) {
            Pedido p;
            while ((p = (Pedido) oisPedidos.readObject()) != null) {
                pedidos.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo pedidos.dat");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }
    }

    private void backupArticulosPorSeccion() {
        List<Articulo> perifericos = new ArrayList<>();
        List<Articulo> almacenamiento = new ArrayList<>();
        List<Articulo> impresoras = new ArrayList<>();
        List<Articulo> monitores = new ArrayList<>();

        for (Articulo a : articulos.values()) {
            switch (a.getArticulo().charAt(0)) {
                case '1' ->
                    perifericos.add(a);
                case '2' ->
                    almacenamiento.add(a);
                case '3' ->
                    impresoras.add(a);
                case '4' ->
                    monitores.add(a);
            }
        }

        try (ObjectOutputStream oosPerifericos = new ObjectOutputStream(new FileOutputStream("archivos/articulos/1-perifericos.dat")); // Comentario 1
                 ObjectOutputStream oosAlmacenamiento = new ObjectOutputStream(new FileOutputStream("archivos/articulos/2-almacenamiento.dat")); // Comentario 2
                 ObjectOutputStream oosImpresoras = new ObjectOutputStream(new FileOutputStream("archivos/articulos/3-impresoras.dat")); // Comentario 3
                 ObjectOutputStream oosMonitores = new ObjectOutputStream(new FileOutputStream("archivos/articulos/4-monitores.dat"))) {

            oosPerifericos.writeObject(perifericos);
            oosAlmacenamiento.writeObject(almacenamiento);
            oosImpresoras.writeObject(impresoras);
            oosMonitores.writeObject(monitores);

            System.out.println("Se han guardado las secciones correctamente");
        } catch (IOException e) {
            System.out.println("No se han podido guardar las secciones correctamente");
        }
    }

    @SuppressWarnings("unused")
    private void leerSecciones() {
        try (ObjectInputStream oisPerifericos = new ObjectInputStream(new FileInputStream("archivos/articulos/1-perifericos.dat")); // Comentario 1
                 ObjectInputStream oisAlmacenamiento = new ObjectInputStream(new FileInputStream("archivos/articulos/2-almacenamiento.dat")); // Comentario 2
                 ObjectInputStream oisImpresoras = new ObjectInputStream(new FileInputStream("archivos/articulos/3-impresoras.dat")); // Comentario 3
                 ObjectInputStream oisMonitores = new ObjectInputStream(new FileInputStream("archivos/articulos/4-monitores.dat"))) {

            List<Articulo> perifericos = (List<Articulo>) oisPerifericos.readObject();
            List<Articulo> almacenamiento = (List<Articulo>) oisAlmacenamiento.readObject();
            List<Articulo> impresoras = (List<Articulo>) oisImpresoras.readObject();
            List<Articulo> monitores = (List<Articulo>) oisMonitores.readObject();

            System.out.println("Periféricos:");
            perifericos.forEach(System.out::println);
            System.out.println("Almacenamiento:");
            almacenamiento.forEach(System.out::println);
            System.out.println("Impresoras:");
            impresoras.forEach(System.out::println);
            System.out.println("Monitores:");
            monitores.forEach(System.out::println);

        } catch (FileNotFoundException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //#endregion
    //#region Examen 19/03/2026
    public void menuExamen1903026() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tMENU DE OPCIONES DEL EXAMEN
                               \t| 0 - SALIR
                               \t| 1 - 
                               \t| 2 - 
                               \t| 3 - 
                               \t| 4 - 
                               \t| 5 - 
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE OPCIONES DEL EXAMEN
                case 1 -> {
                    examenUno();
                }
                case 2 -> {
                    examenDos();
                }
                case 3 -> {
                    examenTres();
                }
                case 4 -> {
                    examenCuatro();
                }
                case 5 -> {
                    examenCinco();
                }
            }
        } while (opcion != 0);
    }

    // Ejercicio 1 -
    public void examenUno() {

    }

    // Ejercicio 2 -
    public void examenDos() {

    }

    // Ejercicio 3 -
    public void examenTres() {

    }

    // Ejercicio 4 -
    public void examenCuatro() {

    }

    // Ejercicio 5 -
    public void examenCinco() {

    }
    //#endregion

    //#endregion
    //#region Examen 20/02/2026
    public void menuExamen2002026() {
        int opcion;
        do {
            System.out.println("\n\tMENU DE OPCIONES DEL EXAMEN");
            System.out.println("\t| 0 - SALIR");
            System.out.println("\t| 1 - Listado de los clientes ordenados de Mayor a Menor por su GASTO");
            System.out.println("\t| 2 - Listado de los articulos de una seccion");
            System.out.println("\t| 3 - Crear y mostrar una NUEVA coleccion (tipo de coleccion libre) llamada articulosNoVendidos");
            System.out.println("\t| 4 - Total facturado en la tienda en los ultimos 5 dias");
            System.out.println("\t| 5 - Importe medio de todos los pedidos de la tienda");

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE OPCIONES DEL EXAMEN
                case 1 -> {
                    ejercicioUno();
                }
                case 2 -> {
                    ejercicioDos();
                }
                case 3 -> {
                    ejercicioTres();
                }
                case 4 -> {
                    ejercicioCuatro();
                }
                case 5 -> {
                    ejercicioCinco();
                }
            }
        } while (opcion != 0);
    }

    // Ejercicio 1 - Listado de los clientes ordenados de Mayor a Menor por su GASTO
    public void ejercicioUno() {
        ArrayList<Cliente> clientesAux = new ArrayList<>(clientes.values());

        clientesAux.stream()
                .sorted(Comparator.comparing(c -> totalGastado(((Cliente) c).getIdCliente())).reversed())
                .forEach(c -> System.out.println(c + "Total Gastado: " + totalGastado(c.getIdCliente())));
    }

    // Ejercicio 2 - Listado de los artículos de una sección
    public void ejercicioDos() {
        sc.nextLine();
        String seccion;

        System.out.println(nombreSecciones[0] + "\n" + nombreSecciones[1] + "\n" + nombreSecciones[2] + "\n" + nombreSecciones[3]);

        do {
            System.out.print("Teclea el numero de la seccion: ");
            seccion = sc.next();
            if (!MetodosAux.esInt(seccion)) {
                System.out.println("Error: Introduce un número valido.");
            }
        } while (!MetodosAux.esInt(seccion));

        String strSeccion = seccion;
        int numSeccion = Integer.parseInt(seccion);

        if (numSeccion >= 1 && numSeccion <= nombreSecciones.length) {
            String nombreSeccion = nombreSecciones[numSeccion - 1];
            System.out.println("\nListados de articulos de la seccion: " + nombreSeccion);

            articulos.values().stream()
                    .filter(a -> a.getArticulo().startsWith(strSeccion) && a.getExistencias() > 0)
                    // .filter(a -> a.getExistencias() > 0)
                    .sorted(Comparator.comparing(Articulo::getPvp).reversed())
                    .forEach(a -> System.out.println(a + "Unidades:\t" + a.getExistencias()));
        } else {
            System.out.println("La seccion seleccionada no existe.");
        }
    }

    // Ejercicio 3 - Crear y mostrar una NUEVA colección (tipo de colección libre) llamada articulosNoVendidos
    public void ejercicioTres() {
        List<Articulo> articulosNoVendidos
                = articulos.values().stream()
                        .filter(a -> totalVendido(a) == 0)
                        .collect(Collectors.toList());
        if (articulosNoVendidos.isEmpty()) {
            System.out.println("Todos los articulos se han vendido al menos una vez.");
        } else {
            articulosNoVendidos.forEach(System.out::println);
        }
    }

    // Ejercicio 4 - Total facturado en la tienda en los últimos 5 días
    public void ejercicioCuatro() {
        LocalDate fechaLimite = LocalDate.now().minusDays(5);
        double total = pedidos.stream()
                .filter(p -> p.getFechaPedido().isAfter(fechaLimite) && p.getFechaPedido().isBefore(hoy))
                .mapToDouble(p -> totalPedido(p))
                .sum();
        System.out.println("Total facturado entre " + fechaLimite + " y " + hoy + ": " + total);
    }

    // Ejercicio 5 -  Importe medio de todos los pedidos de la tienda
    public void ejercicioCinco() {
        double media = pedidos.stream()
                .mapToDouble(p -> totalPedido(p))
                .average().orElse(0.0);
        System.out.println("Importe medio Pedidos TIENDA: " + media);
    }
    //#endregion

    //#region Examen 05/02/2026
    public void menuExamen0502026() {
        int opcion;
        do {
            System.out.println("\n\tMENU DE OPCIONES DEL EXAMEN");
            System.out.println("\t| 0 - SALIR");
            System.out.println("\t| 1 - LISTADO DE ARTICULOS DE UNA SECCION");
            System.out.println("\t| 2 - LISTADO TOTAL DE ARTICULOS POR SECCION");
            System.out.println("\t| 3 - PEDIDOS DE UN CLIENTE Y TOTAL GASTADO");
            System.out.println("\t| 4 - LISTADO DE TODOS LOS ARTICULOS, SEGUN LAS UNIDADES VENDIDAS DE CADA UNO ORDENADO DE > A <");
            System.out.println("\t| 5 - LISTADO DE LOS CLIENTES SIN PEDIDO");

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE OPCIONES DEL EXAMEN
                case 1 -> {
                    uno(); // articulosSeccion
                }
                case 2 -> {
                    dos();
                }
                case 3 -> {
                    tres(); // pedidosCliente
                }
                case 4 -> {
                    cuatro(); // articulosUnidadesVendidas
                }
                case 5 -> {
                    cinco(); // clientesSinPedido
                }
            }
        } while (opcion != 0);
    }

    // Ejercicio 1 - articulosSeccion
    public void uno() {
        sc.nextLine();
        ArrayList<Articulo> articulosAux = new ArrayList<>(articulos.values());
        String seccion;

        System.out.println(nombreSecciones[0] + "\n" + nombreSecciones[1] + "\n" + nombreSecciones[2] + "\n" + nombreSecciones[3]);

        do {
            System.out.print("Teclea el numero de la seccion: ");
            seccion = sc.next();
            if (!MetodosAux.esInt(seccion)) {
                System.out.println("Error: Introduce un numero valido.");
            }
        } while (!MetodosAux.esInt(seccion));

        String strSeccion = seccion;
        int numSeccion = Integer.parseInt(seccion);

        if (numSeccion >= 1 && numSeccion <= nombreSecciones.length) {
            String nombreSeccion = nombreSecciones[numSeccion - 1];
            System.out.println("\nListados de articulos de la seccion: " + nombreSeccion);

            articulosAux.stream()
                    .filter(a -> a.getArticulo().startsWith(strSeccion))
                    .forEach(System.out::println);
        } else {
            System.out.println("La seccion seleccionada no existe.");
        }
    }

    // Ejercicio 2 - listarTodasSecciones
    public void dos() {
        ArrayList<Articulo> articulosAux = new ArrayList<>(articulos.values());

        System.out.println("\nListados de articulos de la seccion: " + nombreSecciones[0]);

        articulosAux.stream()
                .filter(a -> a.getArticulo().startsWith("1"))
                .forEach(System.out::println);

        System.out.println("\nListados de articulos de la seccion: " + nombreSecciones[1]);

        articulosAux.stream()
                .filter(a -> a.getArticulo().startsWith("2"))
                .forEach(System.out::println);

        System.out.println("\nListados de articulos de la seccion: " + nombreSecciones[2]);

        articulosAux.stream()
                .filter(a -> a.getArticulo().startsWith("3"))
                .forEach(System.out::println);

        System.out.println("\nListados de articulos de la seccion: " + nombreSecciones[3]);

        articulosAux.stream()
                .filter(a -> a.getArticulo().startsWith("4"))
                .forEach(System.out::println);

    }

    // Ejercicio 3 - pedidosCliente y totalGastado
    public void tres() {
        sc.nextLine();
        String idCliente;
        do {
            System.out.print("DNI CLIENTE: ");
            idCliente = sc.nextLine().toUpperCase().trim();
            if (!clientes.containsKey(idCliente)) {
                System.out.println("No eres cliente");
            }
        } while (!MetodosAux.validarDNI(idCliente));
        System.out.println();
        String strIdCliente = idCliente;

        System.out.println("\nListado de pedidos por clientes");
        pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equals(strIdCliente))
                .forEach(p -> System.out.println(p + "Total:\t" + totalPedido(p))
                );
        System.out.println("El cliente se ha gastado en total: " + totalGastado(idCliente));
        System.out.println();
    }

    // Ejercicio 4 - articulosUnidadesVendidas
    public void cuatro() {
        ArrayList<Articulo> articulosAux = new ArrayList<>(articulos.values());

        System.out.println("\nListado de articulos y unidades vendidas");
        articulosAux.stream()
                .sorted(Comparator.comparing(a -> totalVendido((Articulo) a)).reversed())
                .forEach(a -> System.out.println(a.getDescription() + "\tTotal vendido: " + totalVendido(a))
                );
    }

    // Ejercicio 5 - clientesSinPedido
    public void cinco() {
        ArrayList<Cliente> clientesAux = new ArrayList<>(clientes.values());
        System.out.println("Clientes sin pedidos:");
        for (Cliente c : clientesAux) {
            int cont = 0;
            for (Pedido p : pedidos) {
                if (c.equals(p.getClientePedido())) {
                    cont++;
                    break;
                }
            }
            if (cont == 0) {
                System.out.println(c.toString());
                System.out.println();
            }
        }
    }

    //#endregion 
    //#region menuOpciones
    public void menuOpciones() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tMENU PRINCIPAL
                               \t| 0 - SALIR
                               \t| 1 - GESTION LISTADOS
                               \t| 2 - GESTION ARTICULOS
                               \t| 3 - GESTION CLIENTES
                               \t| 4 - GESTION PEDIDOS
                               \t| 5 - GESTION ARCHIVOS
                               \t| 6 - EXAMEN 05/02/2026
                               \t| 7 - EXAMEN 20/02/2026
                               \t| 8 - PRUEBAS / EJERCICIOS
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE OPCIONES
                case 1 -> {
                    menuListados();
                }
                case 2 -> {
                    menuArticulos();
                }
                case 3 -> {
                    menuClientes();
                }
                case 4 -> {
                    menuPedidos();
                }
                case 5 -> {
                    menuArchivos();
                }
                case 6 -> {
                    menuExamen0502026();
                }
                case 7 -> {
                    menuExamen2002026();
                }
                case 8 -> {
                    menuEjercicios();
                }
            }
        } while (opcion != 0);
    }

    //#endregion
    //#region Listados
    public void menuListados() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tMENU DE LISTADOS
                               \t| 0 - SALIR
                               \t| 1 - LISTADO TOTAL (TODO)
                               \t| 2 - LISTADO ARTICULOS
                               \t| 3 - LISTADO CLIENTES
                               \t| 4 - LISTADO PEDIDOS
                               \t| 5 - DEMOSTRACION STREAMS
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE LISTADOS
                case 1 -> {
                    listado();
                }
                case 2 -> {
                    listadoArticulos();
                }
                case 3 -> {
                    listadoClientes();
                }
                case 4 -> {
                    listadoPedidos();
                }
                case 5 -> {
                    listadosStreams();
                }
            }
        } while (opcion != 0);
    }

    public void listado() {
        listadoArticulos();
        listadoClientes();
        listadoPedidos();
    }

    public void listadoArticulos() {
        System.out.println();
        for (Articulo a : articulos.values()) {
            System.out.println(a);
        }

        articulos.values().stream().forEach(a -> System.out.println(a));

        ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());

        System.out.println("\nListados de articulos que valgan menos de 100");
        articulosAux.stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .sorted(Comparator.comparing(a -> a.getPvp()))
                .forEach(System.out::println);
    }

    public void listadoClientes() {
        System.out.println();
        for (Cliente c : clientes.values()) {
            System.out.println(c);
        }
    }

    public void listadoPedidos() {
        System.out.println();
        for (Pedido p : pedidos) {
            System.out.println(p + "Total:\t" + totalPedido(p));
        }

        System.out.println("\nListados de menor a mayor");
        pedidos.stream()
                .sorted(Comparator.comparing(p -> totalPedido(p)))
                .forEach(p -> System.out.println(p + "Total:\t" + totalPedido(p))
                );

        System.out.println("\nListados de mayor a menor");
        pedidos.stream()
                .sorted(Comparator.comparing(p -> totalPedido((Pedido) p)).reversed())
                .forEach(p -> System.out.println(p + "Total:\t" + totalPedido(p))
                );

        System.out.println("\n");
    }

    //#endregion
    //#region Articulos
    public void menuArticulos() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tGESTION DE ARTICULOS
                               \t| 0 - SALIR
                               \t| 1 - LISTADO ARTICULOS
                               \t| 2 - ALTA ARTICULO
                               \t| 3 - BAJA ARTICULO
                               \t| 4 - REPOSICION STOCK
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE ARTICULOS
                case 1 -> {
                    listadoArticulos();
                }
                case 2 -> {
                    altaArticulo();
                }
                case 3 -> {
                    bajaArticulo();
                }
                case 4 -> {
                    reposicionArticulos();
                }
            }
        } while (opcion != 0);
    }

    private void altaArticulo() {

        String idArticulo, descripcion, existencias, pvp; // TODAS LAS ENTRADAS COMO STRING FACILITA VALIDACION Y EVITA PROBLEMAS CON SCANNER

        System.out.println("ALTA DE NUEVO ARTICULO");
        //idArticulo VALIDADO CON EXPRESION REGULAR SENCILLA
        do {
            System.out.println("IdArticulo (IDENTIFICADOR):");
            idArticulo = sc.nextLine();
        } while (!idArticulo.matches("[1-6][-][0-9][0-9]") || articulos.containsKey(idArticulo));
        //OJO CONTROLAR tambien QUE NO EXISTA ESE ID PREVIAMENTE

        //ENTRADA DESCRIPCION SIN NINGUN TIPO DE VALIDACION
        System.out.println("DESCRIPCION:");
        descripcion = sc.nextLine();

        // EXISTENCIAS CON VALIDACION DE TIPO int
        do {
            System.out.println("EXISTENCIAS:");
            existencias = sc.nextLine(); //Se lee la entrada de EXISTENCIAS como un String
        } while (!MetodosAux.esInt(existencias)); //Se sigue pidiendo la entrada si no es int

        // PVP CON VALIDACION DE TIPO double
        do {
            System.out.println("PVP:");
            pvp = sc.nextLine(); //Se lee la entrada del PVP como un String
        } while (!MetodosAux.esDouble(pvp)); //Se sigue pidiendo la entrada si no es double

        //AÑADO OBJETO ARTICULO A LA COLECCION PARSEANDO A int y double los datos de existencias y PVP
        Articulo a = new Articulo(idArticulo, descripcion,
                Integer.parseInt(existencias), Double.parseDouble(pvp));
        articulos.put(idArticulo, a);
        System.out.println("- Articulo añadido -");
        /* por supuesto podría haberlo hecho con una única istrucción
        articulos.put(idArticulo,new Articulo(idArticulo,descripcion,Integer.parseInt(existencias),Double.parseDouble(pvp)));
         */
    }

    public void bajaArticulo() {
        sc.nextLine();
        System.out.println("BAJA DE ARTICULO");
        System.out.print("Introduce el ID del articulo a eliminar: ");
        String id = sc.nextLine();

        if (articulos.containsKey(id)) {
            // Comprobación opcional: No borrar si hay pedidos vinculados (para integridad referencial)
            articulos.remove(id);
            System.out.println("Articulo eliminado correctamente.");
        } else {
            System.out.println("El articulo con ID " + id + " no existe.");
        }
    }

    public void reposicionArticulos() {
        sc.nextLine();
        System.out.println("REPOSICION DE STOCK");
        System.out.print("Introduce el ID del articulo: ");
        String id = sc.nextLine();

        if (articulos.containsKey(id)) {
            Articulo a = articulos.get(id);
            System.out.println("Stock actual: " + a.getExistencias());
            System.out.print("Unidades a añadir: ");
            String cantStr = sc.nextLine();
            if (MetodosAux.esInt(cantStr)) {
                a.setExistencias(a.getExistencias() + Integer.parseInt(cantStr));
                System.out.println("Stock actualizado. Nuevo stock: " + a.getExistencias());
            } else {
                System.out.println("Cantidad no valida.");
            }
        } else {
            System.out.println("Articulo no encontrado.");
        }
    }

    //#endregion
    //#region Clientes
    public void menuClientes() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tGESTION DE CLIENTES
                               \t| 0 - SALIR
                               \t| 1 - LISTADO CLIENTES
                               \t| 2 - ALTA CLIENTE
                               \t| 3 - BAJA CLIENTE
                               \t| 4 - MODIFICAR CLIENTE
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE CLIENTES
                case 1 -> {
                    listadoClientes();
                }
                case 2 -> {
                    altaCliente();
                }
                case 3 -> {
                    bajaCliente();
                }
                case 4 -> {
                    modificarCliente();
                }
            }
        } while (opcion != 0);
    }

    public void altaCliente() {
        sc.next();
        String idCliente;
        System.out.print("Teclea tu DNI: ");
        do {
            idCliente = sc.nextLine();
        } while (!MetodosAux.validarDNI(idCliente));
        System.out.println("Teclea tu nombre");
        String nombre = sc.nextLine().toUpperCase().trim();
        System.out.println("Teclea tu telefono");
        String telefono = sc.nextLine().trim();
        System.out.println("Teclea tu email");
        String email = sc.nextLine().trim();
        clientes.put(idCliente, new Cliente(idCliente, nombre, telefono, email));

    }

    public void bajaCliente() {
        sc.nextLine();
        System.out.println("BAJA DE CLIENTE");
        System.out.print("Introduce el DNI del cliente: ");
        String id = sc.nextLine().toUpperCase();

        if (clientes.containsKey(id)) {
            // Comprobar si tiene pedidos antes de borrar
            List<Pedido> pedidosCliente = pedidos.stream()
                    .filter(p -> p.getClientePedido().getIdCliente().equals(id))
                    .collect(Collectors.toList());

            if (!pedidosCliente.isEmpty()) {
                System.out.println("Este cliente tiene " + pedidosCliente.size() + " pedido(s) asociado(s).");
                System.out.print("Si no elimina sus pedidos no se podra eliminar el cliente. (S/N) ");
                if (sc.nextLine().equalsIgnoreCase("S")) {
                    // Devolver stock y eliminar pedidos
                    for (Pedido p : pedidosCliente) {
                        for (LineaPedido lp : p.getCestaCompra()) {
                            Articulo a = lp.getArticulo();
                            a.setExistencias(a.getExistencias() + lp.getUnidades());
                        }
                    }
                    pedidos.removeAll(pedidosCliente);
                    System.out.println("Pedidos del cliente eliminados y stock restaurado.");
                } else {
                    System.out.println("Operacion cancelada. No se puede dar de baja un cliente con pedidos en curso.");
                    return; // Salir del método
                }
            }

            // Si llega aquí, o no tenía pedidos, o ya se han borrado.
            System.out.print("Esta seguro de que desea eliminar al cliente " + clientes.get(id).getNombre() + " (S/N) ");
            if (sc.nextLine().equalsIgnoreCase("S")) {
                clientes.remove(id);
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("Baja de cliente cancelada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void modificarCliente() {
        sc.nextLine();
        System.out.println("MODIFICAR CLIENTE");
        System.out.print("Introduce el DNI del cliente: ");
        String id = sc.nextLine().toUpperCase();

        if (clientes.containsKey(id)) {
            Cliente c = clientes.get(id);
            System.out.println("Datos actuales: " + c);
            System.out.println("Introduce nuevos datos (pulsa ENTER para mantener el actual):");

            System.out.print("Nombre (" + c.getNombre() + "): ");
            String nombre = sc.nextLine().trim().toUpperCase();
            if (!nombre.isEmpty()) {
                c.setNombre(nombre);
            }

            System.out.print("Telefono (" + c.getTelefono() + "): ");
            String tlf = sc.nextLine().trim();
            if (!tlf.isEmpty()) {
                c.setTelefono(tlf);
            }

            System.out.print("Email (" + c.getEmail() + "): ");
            String email = sc.nextLine().trim();
            if (!email.isEmpty()) {
                c.setEmail(email);
            }

            System.out.println("Cliente actualizado.");

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    //#endregion
    //#region Pedidos
    public void menuPedidos() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tGESTION DE PEDIDOS
                               \t| 0 - SALIR
                               \t| 1 - LISTADO PEDIDOS
                               \t| 2 - NUEVO PEDIDO
                               \t| 3 - CONSULTAR TOTAL PEDIDO (ID)
                               \t| 4 - ELIMINAR PEDIDO
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE PEDIDOS
                case 1 -> {
                    listadoPedidos();
                }
                case 2 -> {
                    nuevoPedido();
                }
                case 3 -> {
                    System.out.print("Teclea una idPedido: ");
                    int idPedido = sc.nextInt();
                    totalPedido(pedidos.get(idPedido));
                    //80580845T-001/2025
                }
                case 4 -> {
                    bajaPedido();
                }
            }
        } while (opcion != 0);
    }

    public void bajaPedido() {
        sc.nextLine(); // Limpiar buffer
        System.out.println("ELIMINAR PEDIDO");
        System.out.print("Introduce el ID del pedido a eliminar: ");
        String id = sc.nextLine();

        Pedido pedidoAEliminar = null;
        for (Pedido p : pedidos) {
            if (p.getIdPedido().equalsIgnoreCase(id)) {
                pedidoAEliminar = p;
                break;
            }
        }

        if (pedidoAEliminar != null) {
            System.out.println("Se encontro el siguiente pedido:");
            System.out.println(pedidoAEliminar);
            System.out.print("Esta seguro de que desea eliminarlo (S/N) Esta accion devolvera el stock. ");
            if (sc.nextLine().equalsIgnoreCase("S")) {
                // Devolver stock
                for (LineaPedido lp : pedidoAEliminar.getCestaCompra()) {
                    Articulo a = lp.getArticulo();
                    a.setExistencias(a.getExistencias() + lp.getUnidades());
                }
                pedidos.remove(pedidoAEliminar);
                System.out.println("Pedido eliminado y stock restaurado.");
            } else {
                System.out.println("Eliminacion cancelada.");
            }
        } else {
            System.out.println("No se ha encontrado ningun pedido con el ID " + id);
        }
    }

    public void stock(Articulo a, int unidades) throws StockCero, StockInsuficiente {
        if (a.getExistencias() == 0) {
            throw new StockCero("0 unidades disponibles de: "
                    + a.getDescription());
        }
        if (a.getExistencias() < unidades) {
            throw new StockInsuficiente("\nSolo hay " + a.getExistencias()
                    + " unidades disponibles de: " + a.getDescription());
        }
    }

    public void nuevoPedido() {
        sc.nextLine(); // Limpiar buffer tras nextInt()
        String idCliente;
        do {
            System.out.print("DNI CLIENTE: ");
            idCliente = sc.nextLine().toUpperCase().trim();
            if (!clientes.containsKey(idCliente)) {
                System.out.println("No eres cliente");
            }
        } while (!MetodosAux.validarDNI(idCliente));

        ArrayList<LineaPedido> cestaCompra = new ArrayList<>();
        String idArticulo;
        int unidades;
        System.out.println("\n\t(FIN para terminar la compra)");
        while (true) {
            System.out.print("Teclea el ID del articulo deseado: ");
            idArticulo = sc.nextLine().trim();
            if (idArticulo.equalsIgnoreCase("fin")) {
                break;
            }
            if (!articulos.containsKey(idArticulo)) {
                System.out.println("Articulo no encontrado.");
                continue;
            }
            System.out.print("Teclea las unidades deseadas: ");
            String unidadesStr = sc.nextLine().trim();
            if (!MetodosAux.esInt(unidadesStr)) {
                System.out.println("Introduce un numero válido.");
                continue;
            }
            unidades = Integer.parseInt(unidadesStr);
            Articulo a = articulos.get(idArticulo);
            try {
                stock(a, unidades);
                cestaCompra.add(new LineaPedido(a, unidades));
            } catch (StockCero ex) {
                System.out.println(ex.getMessage());
            } catch (StockInsuficiente ex) {
                System.out.println(ex.getMessage());
                System.out.print("Las quieres (Si/No) ");
                String respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    int stockDisponible = a.getExistencias();
                    if (stockDisponible > 0) {
                        cestaCompra.add(new LineaPedido(a, stockDisponible));
                        a.setExistencias(0);
                    }
                }
            }
        }
        if (!cestaCompra.isEmpty()) {
            System.out.println("Este es tu pedido: ");
            for (LineaPedido l : cestaCompra) {
                System.out.println(l.getArticulo() + "\t- "
                        + l.getArticulo().getDescription() + "\t- "
                        + l.getUnidades() + "\t- "
                        + l.getArticulo().getPvp() + "\t- "
                        + l.getArticulo().getPvp() * l.getUnidades());
            }
            System.out.print("Procedemos con la compra (Si/No): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("si")) {
                String idPedido = generaIdPedido(idCliente);
                pedidos.add(new Pedido(idPedido, clientes.get(idCliente), hoy, cestaCompra));
                for (LineaPedido l : cestaCompra) {
                    l.getArticulo().setExistencias(
                            l.getArticulo().getExistencias() - l.getUnidades());
                }
                System.out.println("Pedido realizado correctamente.");
            }
        }
    }

    public double totalPedido(Pedido p) {
        double totalPedido = 0;
        for (LineaPedido l : p.getCestaCompra()) {
            totalPedido += l.getUnidades() * l.getArticulo().getPvp();
        }
        return totalPedido;
    }

    public int totalVendido(Articulo a) {
        int totalVendido = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getCestaCompra()) {
                if (a.getArticulo().equals(l.getArticulo().getArticulo())) {
                    totalVendido += l.getUnidades();
                }
            }
        }
        return totalVendido;
    }

    private double totalGastado(String idCliente) {
        int total = 0;
        for (Pedido p : pedidos) {
            if (idCliente.equals(p.getClientePedido().getIdCliente())) {
                total += totalPedido(p);
                System.out.println();
            }
        }
        return total;
    }

    public String generaIdPedido(String idCliente) {
        int contador = 0;
        String nuevoId;
        for (Pedido p : pedidos) {
            if (p.getClientePedido().getIdCliente().equalsIgnoreCase(idCliente)) {
                contador++;
            }
        }
        contador++;
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + hoy.getYear();
        return nuevoId;
    }

    //#endregion
    //#region Archivos
    public void menuArchivos() {
        // secciones distintas a csv
        // clientes con pedidos y clientes sin pedidos
        int opcion;
        do {
            System.out.println("""
                               
                               \t MENU DE ARCHIVOS
                               \t | 0 - SALIR
                               \t | 1 - INFORMACION ARCHIVO
                               \t | 2 - BORRAR ARCHIVO
                               \t | 3 - CAMBIAR NOMBRE ARCHIVO
                               \t | 4 - ESCRIBIR ARCHIVO
                               \t | 5 - LEER ARCHIVO
                               \t | 6 - GUARDAR CLIENTES
                               \t | 7 - LEE CLIENTES
                               \t | 8 - GUARDA ARTICULOS POR SECCION
                               \t | 9 - LEE ARTICULOS
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE ARCHIVOS
                case 1 -> {
                    infoArchivo();
                }
                case 2 -> {
                    borrarArchivo();
                }
                case 3 -> {
                    cambiarNombreArchivo();
                }
                case 4 -> {
                    escribirArchivo();
                }
                case 5 -> {
                    leerArchivo();
                }
                case 6 -> {
                    guardarClientes();
                }
                case 7 -> {
                    leeClientes();
                }
                case 8 -> {
                    guardaArticulosPorSeccion();
                }
                case 9 -> {
                    leeArticulos();
                }
            }
        } while (opcion != 0);
    }

    public static void infoArchivo() {
        File f = new File("archivos/archivo1.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Nombre: " + f.getName());
        System.out.println("Ruta: " + f.getAbsolutePath());
        System.out.println("Tamano en Bytes: " + f.length());
        System.out.println("Fecha ultima modificacion: " + new Date(f.lastModified()));
    }

    public static void borrarArchivo() {
        System.out.println("Archivo a eliminar: ");
        String nombre = sc.nextLine();
        File f = new File(nombre);
        System.out.println(f.getAbsolutePath());
        if (f.delete()) {
            System.out.println("Archivo eliminado");
        } else {
            System.out.println("No se ha podido eliminar");
        }
    }

    public static void cambiarNombreArchivo() {
        System.out.println("Nombre del Archivo a renombrar: ");
        String nombre = sc.nextLine();
        File f1 = new File(nombre);
        System.out.println("Nuevo nombre:");
        String nombre2 = sc.nextLine();
        File f2 = new File(nombre2);
        if (f1.renameTo(f2)) {
            System.out.println("Se ha cambiado el nombre");
        } else {
            System.out.println("No se ha podido cambiar el nombre");
        }
    }

    public static void escribirArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/archivo1.txt", true))) {
            String cadena;
            System.out.println("Teclea lineas de texto + RETORNO - (FIN para terminar)");
            cadena = sc.nextLine();
            while (!cadena.equalsIgnoreCase("FIN")) {
                bw.write(cadena); //Escribe la cadena en el BufferedWriter
                bw.newLine(); //Añade un salto de línea
                cadena = sc.nextLine(); //Solicita una nueva cadena
            }
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

    public static void leerArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("archivos/archivo1.txt"))) {
            String cadena = br.readLine(); //Lee la primera línea del fichero
            while (cadena != null) { //Mientras no se llegue al final del archivo
                System.out.println(cadena); //Se nuestra por pantalla
                cadena = br.readLine(); //Se lee la siguiente línea del archivo
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void guardarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/clientes/clientes.txt", true))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("clientes.txt actualizado");
        } catch (IOException e) {
            System.out.println("No se han podido escribir los clientes en el archivo");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/clientes/clientes.csv", true))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("clientes.csv actualizado");
        } catch (IOException e) {
            System.out.println("No se han podido escribir los clientes en el archivo");
        }
    }

    public void leeClientes() {
        /*
        try (Scanner scClientes = new Scanner(new File("archivos/clientes/clientes.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                System.out.println(c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
         */
        HashMap<String, Cliente> clientesAux = new HashMap();
        try (Scanner scClientes = new Scanner(new File("archivos/clientes/clientes.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        clientesAux.values().forEach(System.out::println);
    }

    private void guardaArticulosPorSeccion() {
        try (
                // Todo esto esta comentado porque si no se bugea al formatear =)
                BufferedWriter bwPerifericos = new BufferedWriter(new FileWriter("archivos/articulos/1-perifericos.csv", true)); // Comentario 1
                 BufferedWriter bwAlmacenamiento = new BufferedWriter(new FileWriter("archivos/articulos/2-almacenamiento.csv", true)); // Comentario 2
                 BufferedWriter bwImpresoras = new BufferedWriter(new FileWriter("archivos/articulos/3-impresoras.csv", true)); // Comentario 3
                 BufferedWriter bwMonitores = new BufferedWriter(new FileWriter("archivos/articulos/4-monitores.csv", true))) {

            for (Articulo a : articulos.values()) {
                switch (a.getArticulo().charAt(0)) {
                    case '1' -> {
                        bwPerifericos.write(a.getArticulo() + "," + a.getDescription() + "," + a.getExistencias() + "," + a.getPvp());
                        bwPerifericos.newLine();
                    }
                    case '2' -> {
                        bwAlmacenamiento.write(a.getArticulo() + "," + a.getDescription() + "," + a.getExistencias() + "," + a.getPvp());
                        bwAlmacenamiento.newLine();
                    }
                    case '3' -> {
                        bwImpresoras.write(a.getArticulo() + "," + a.getDescription() + "," + a.getExistencias() + "," + a.getPvp());
                        bwImpresoras.newLine();
                    }
                    case '4' -> {
                        bwMonitores.write(a.getArticulo() + "," + a.getDescription() + "," + a.getExistencias() + "," + a.getPvp());
                        bwMonitores.newLine();
                    }
                }
            }
            System.out.println("Se han podido escribir los artiuclos en los archivos .csv");
        } catch (IOException e) {
            System.out.println("No se han podido escribir los artiuclos en los archivos .csv");
        }
        /* Mas bonito a la vista, pero recorres todas las secciones y todos los articulos varias veces
        for (String seccion : nombreSecciones) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/articulos/" + seccion + ".csv", true))) {
                for (Articulo a : articulos.values()) {
                    if (a.getArticulo().startsWith(seccion.substring(0, 1))) {
                    bw.write(a.getArticulo() + "," + a.getDescription() + "," + a.getExistencias() + "," + a.getPvp());
                    bw.newLine(); 
                    }
                }
                System.out.println(seccion + ".csv actualizado");
            } catch (IOException e) {
                System.out.println("No se han podido escribir los clientes en el archivo");
            }
        }
        System.out.println();
         */
    }

    public void leeArticulos() {
        HashMap<String, Articulo> Perifericos = new HashMap();
        HashMap<String, Articulo> Almacenamiento = new HashMap();
        HashMap<String, Articulo> Impresoras = new HashMap();
        HashMap<String, Articulo> Monitores = new HashMap();

        try (
                // Todo esto esta comentado porque si no se bugea al formatear =)
                Scanner scPerifericos = new Scanner(new File("archivos/articulos/1-perifericos.csv")); // Comentario 1
                 Scanner scAlmacenamiento = new Scanner(new File("archivos/articulos/2-almacenamiento.csv")); // Comentario 2
                 Scanner scImpresoras = new Scanner(new File("archivos/articulos/3-impresoras.csv")); // Comentario 3
                 Scanner scMonitores = new Scanner(new File("archivos/articulos/4-monitores.csv"))) {

            while (scPerifericos.hasNextLine()) {
                String[] atributos = scPerifericos.nextLine().split("[,]");
                Articulo a = new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3]));
                Perifericos.put(atributos[0], a);
            }
            while (scAlmacenamiento.hasNextLine()) {
                String[] atributos = scAlmacenamiento.nextLine().split("[,]");
                Articulo a = new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3]));
                Almacenamiento.put(atributos[0], a);
            }
            while (scImpresoras.hasNextLine()) {
                String[] atributos = scImpresoras.nextLine().split("[,]");
                Articulo a = new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3]));
                Impresoras.put(atributos[0], a);
            }
            while (scMonitores.hasNextLine()) {
                String[] atributos = scMonitores.nextLine().split("[,]");
                Articulo a = new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3]));
                Monitores.put(atributos[0], a);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        System.out.println("\t 1 - Perifericos");
        Perifericos.values().forEach(System.out::println);

        System.out.println("\t 2 - Almacenamiento");
        Almacenamiento.values().forEach(System.out::println);

        System.out.println("\t 3 - Impresoras");
        Impresoras.values().forEach(System.out::println);

        System.out.println("\t 4 - Monitores");
        Monitores.values().forEach(System.out::println);
    }
    //#endregion
    //#region Ejercicios

    public void menuEjercicios() {
        int opcion;
        do {
            System.out.println("""
                               
                               \tMENU DE EJERCICIOS (STREAMS)
                               \t| 0 - SALIR
                               \t| 1 - Unidades totales vendidas por Articulo
                               \t| 2 - Clientes ordenados por gasto total
                               \t| 3 - Articulos con stock bajo (< 5)
                               \t| 4 - Pedidos agrupados por Cliente (Cantidad)
                               \t| 5 - Facturacion total de la tienda
                               """);

            System.out.print("Teclea el numero: ");

            opcion = sc.nextInt();
            System.out.println();

            switch (opcion) {
                // MENU DE OPCIONES
                case 1 -> {
                    for (Articulo a : articulos.values()) {
                        int total = 0;
                        for (Pedido p : pedidos) {
                            total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                                    .mapToInt(LineaPedido::getUnidades).sum();
                        }
                        System.out.println(a + " - " + total);
                    }
                }
                case 2 -> {
                    // Clientes ordenados por gasto
                    clientes.values().stream()
                            .sorted(Comparator.comparing(c -> totalGastado(((Cliente) c).getIdCliente())).reversed())
                            .forEach(c -> System.out.println(c.getNombre() + " - Gasto Total: " + totalGastado(c.getIdCliente()) + "€"));
                }
                case 3 -> {
                    // Stock bajo
                    System.out.println("--- ARTICULOS CON STOCK BAJO (<5) ---");
                    articulos.values().stream()
                            .filter(a -> a.getExistencias() < 5)
                            .sorted(Comparator.comparing(Articulo::getExistencias))
                            .forEach(a -> System.out.println(a.getDescription() + " - Stock: " + a.getExistencias()));
                }
                case 4 -> {
                    // Agrupación por cliente
                    Map<String, Long> pedidosPorCliente = pedidos.stream()
                            .collect(Collectors.groupingBy(p -> p.getClientePedido().getNombre(), Collectors.counting()));

                    pedidosPorCliente.forEach((nombre, cantidad)
                            -> System.out.println("Cliente: " + nombre + " - Pedidos: " + cantidad));
                }
                case 5 -> {
                    // Facturación total
                    double facturacionTotal = pedidos.stream()
                            .mapToDouble(this::totalPedido)
                            .sum();
                    System.out.println("Facturacion Total de la Tienda: " + String.format("%.2f", facturacionTotal) + "€");
                }
            }
        } while (opcion != 0);
    }

    //#endregion
    //#region Streams
    private void listadosStreams() {

        //EJEMPLOS SENCILLOS CON filter() - sorted() - forEach()
        // ARTICULOS DE MENOS DE 100€ ORDENADOS POR PRECIO DE - A +
        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));

        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE - A + (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido(p)))
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE + A - (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido((Pedido) p)).reversed())
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //PEDIDOS DE MÁS DE 1000€ (filter) ORDENADOS POR LA FECHA DEL PEDIDO DE - A + 
        System.out.println("\n");
        pedidos.stream().filter(p -> totalPedido(p) > 1000)
                .sorted(Comparator.comparing(Pedido::getFechaPedido))
                .forEach(p -> System.out.println(p + "- Total: " + p.getFechaPedido()));

        //EJERCICIOS CON MÉTODOS DEL API PARA REALIZAR CALCULOS count() map() mapToInt() .collect(Collectors.groupingBy) ...
        //CONTABILIZAR LOS PEDIDOS DE UN DETERMINADO CLIENTE - PODRÍA PEDIR NOMBRE O DNI POR TECLADO PERO LO HARÉ PARA UNO CONCRETO
        long numPedidos = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println("\n" + numPedidos + "\n");
        //LAS FUNCIONES TIPO count() counting() almacenan resultados en variables de tipo long 

        //CONTABILIZAR CUANTOS PEDIDOS HAY POR CLIENTE - PARA LAS AGRUPACIONES SON IDEALES LOS MAPAS PORQUE PUEDEN CONTENER 2 DATOS
        Map<Cliente, Long> numPedidosPorCliente
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));

        for (Cliente c : numPedidosPorCliente.keySet()) {
            System.out.println(c + " - " + numPedidosPorCliente.get(c));
        }

        // TOTAL DE UNIDADES VENDIDAS DE UN ARTICULO EN TODOS LOS PEDIDOS. PODEMOS APLICARLO AL 
        // MÉTODO UNIDADES VENDIDAS QUE HABÍA QUE HACER EN EL EJERCICIO 4 DE LA ÚLTIMA PRUEBA
        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            System.out.println(a + " - " + total);
        }

        /**
         * **************************************************************************************
         * EJERCICIOS CON flatMap() para colecciones anidadas, nuestro caso pues
         * pedidos es un ArrayList de <Pedido> y dentro de cada Pedido hay una
         * cestaCompra, que es un ArrayList de <Lineapedido>
         *
         ****************************************************************************************
         */
        //USUARIOS QUE HAN COMPRADO UN ARTÍCULO DETERMINADO incluyendo CUANTAS UNIDADES HAN COMPRADO 
        //probamos con el artículo articulos.get("4-22") 
        System.out.println("\n");
        for (Cliente c : clientes.values()) {
            int unidades = pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                    .flatMap(p -> p.getCestaCompra().stream()).filter(l -> l.getArticulo().equals(articulos.get("4-22")))
                    .mapToInt(LineaPedido::getUnidades).sum();

            System.out.println(c.getNombre() + ": " + unidades + " de " + articulos.get("4-22").getDescription());
        }

        //TODOS LOS ARTICULOS VENDIDOS, LOS ALMACENAMOS EN UN SET PARA EVITAR REPETICIONES
        System.out.println("\n");

        Set<Articulo> articulosVendidos
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

        articulosVendidos.stream().forEach(a -> System.out.println(a));

        //TOTAL DE UNIDADES VENDIDAS DE TODOS LOS ARTÍCULOS usando flatMap()
        System.out.println("\n");
        Map<Articulo, Integer> unidadesPorArticulo
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .collect(Collectors.groupingBy(LineaPedido::getArticulo, Collectors.summingInt(LineaPedido::getUnidades)
                        ));
        for (Articulo a : unidadesPorArticulo.keySet()) {
            System.out.println(a.getDescription() + " - " + unidadesPorArticulo.get(a));
        }

        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(a -> a.getPvp()))
                .forEach(System.out::println);

        System.out.println("\nListados de mayor a menor");
        pedidos.stream()
                .sorted(Comparator.comparing(p -> totalPedido((Pedido) p)).reversed())
                .forEach(p -> System.out.println(p + "Total:\t" + totalPedido(p))
                );

        long numPedidos1 = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println(numPedidos1);
        Map<Cliente, Long> numPedidosPorCliente1
                = pedidos.stream().collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));
        System.out.println(numPedidosPorCliente1);

        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream()
                        .filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades)
                        .sum();
            }
            System.out.println(a + " - " + total);
        }

        System.out.println("Listado de todos los pedidos ordenados por fecha de menor a mayor y almacenados en una lista:");
        List<Pedido> pedidosOrdenadosFecha
                = pedidos.stream().sorted(Comparator.comparing(Pedido::getFechaPedido))
                        .collect(Collectors.toList());
        System.out.println("Listado de todos los pedidos ordenados por el total de menor a mayor y almacenados en una coleccion TreeMap:");
        TreeMap<Double, Pedido> pedidosConTotales = new TreeMap();
        for (Pedido p : pedidos) {
            pedidosConTotales.put(totalPedido(p), p);
        }
        System.out.println("\n");
        for (Double total : pedidosConTotales.keySet()) {
            System.out.println(pedidosConTotales.get(total).getIdPedido() + " - " + total);
        }
        System.out.println("Listado de todos los pedidos ordenados por el total de mayor a menor y almacenados en una coleccion TreeMap:");
        TreeMap<Double, Pedido> pedidosConTotales2 = new TreeMap();
        for (Pedido p : pedidos) {
            pedidosConTotales2.put(totalPedido(p), p);
        }
        System.out.println("\n");
        for (Double total : pedidosConTotales2.descendingKeySet()) {
            System.out.println(pedidosConTotales2.get(total).getIdPedido() + " - " + total);
        }
        System.out.println("Listado de todos los clientes ordenados por las ventas realizadas de mayor a menor y almacenados en una coleccion TreeMap:");
        TreeMap<Double, Cliente> ventasPorCliente = new TreeMap();
        for (Cliente c : clientes.values()) {
            ventasPorCliente.put(totalCliente2(c), c);
        }
        System.out.println("\n");
        for (Double totalPorCliente : ventasPorCliente.descendingKeySet()) {
            System.out.println(ventasPorCliente.get(totalPorCliente).getNombre() + " - " + totalPorCliente);
        }
        System.out.println(pedidosOrdenadosFecha);
    }

    public void pruebas() {
        List<Articulo> perifericos, almacenamiento, impresoras, monitores;
        perifericos = articulos.values()
                .stream().filter(a -> a.getArticulo().startsWith("1"))
                .collect(Collectors.toList());

        almacenamiento = articulos.values()
                .stream().filter(a -> a.getArticulo().startsWith("2"))
                .collect(Collectors.toList());

        impresoras = articulos.values()
                .stream().filter(a -> a.getArticulo().startsWith("3"))
                .collect(Collectors.toList());

        monitores = articulos.values()
                .stream().filter(a -> a.getArticulo().startsWith("4"))
                .collect(Collectors.toList());

        articulos.values()
                .removeIf(a -> a.getArticulo().startsWith("3"));

        System.out.println(perifericos);
        System.out.println(almacenamiento);
        System.out.println(impresoras);
        System.out.println(monitores);
    }

    //Total gastado por un Cliente
    public double totalCliente2(Cliente c) {
        return pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                .mapToDouble(p -> totalPedido(p)).sum();
    }

    //Importe Total de un Pedido
    public double totalPedido2(Pedido p) {
        return p.getCestaCompra().stream()
                .mapToDouble(l -> l.getArticulo().getPvp()
                * l.getUnidades()).sum();
    }

    /*
        METODO PARA CALCULAR LAS UNIDADES VENDIDAS DE UN ARTÍCULO
        EN TODOS LOS PEDIDOS DE LA TIENDA - VERSIÓN CLÁSICA

        private int unidadesVendidas1(Articulo a) {
            int c = 0;
            for (Pedido p : pedidos) {
                for (LineaPedido l : p.getCestaCompra()) {
                    if (l.getArticulo().equals(a)) {
                        c += l.getUnidades();
                    }
                }
            }
            return c;
        }
     */
 /*
        VERSIÓN SEMI-CLÁSICA - UTILIZANDO STREAMS SOLO PARA LA cestaCompra de cada Pedido

        private int unidadesVendidas2(Articulo a) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            return total;
        }
     */
 /*
        VERSIÓN PROGRAMACIÓN FUNCIONAL CON flatMap() para aplanar los streams

        private int unidadesVendidas3(Articulo a) {
            return pedidos.stream().flatMap(p -> p.getCestaCompra().stream())
                    .filter(l -> l.getArticulo().equals(a))
                    .mapToInt(LineaPedido::getUnidades).sum();
        }
     */
    //#endregion
}

