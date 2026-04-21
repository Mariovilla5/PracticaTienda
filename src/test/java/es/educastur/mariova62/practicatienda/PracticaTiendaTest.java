/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package es.educastur.mariova62.practicatienda;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 1dawd07
 */
public class PracticaTiendaTest {
    
    public PracticaTiendaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPedidos method, of class PracticaTienda.
     */
    @Test
    public void testGetPedidos() {
        System.out.println("getPedidos");
        PracticaTienda instance = new PracticaTienda();
        ArrayList<Pedido> expResult = null;
        ArrayList<Pedido> result = instance.getPedidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPedidos method, of class PracticaTienda.
     */
    @Test
    public void testSetPedidos() {
        System.out.println("setPedidos");
        ArrayList<Pedido> pedidos = null;
        PracticaTienda instance = new PracticaTienda();
        instance.setPedidos(pedidos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticulos method, of class PracticaTienda.
     */
    @Test
    public void testGetArticulos() {
        System.out.println("getArticulos");
        PracticaTienda instance = new PracticaTienda();
        HashMap<String, Articulo> expResult = null;
        HashMap<String, Articulo> result = instance.getArticulos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArticulos method, of class PracticaTienda.
     */
    @Test
    public void testSetArticulos() {
        System.out.println("setArticulos");
        HashMap<String, Articulo> articulos = null;
        PracticaTienda instance = new PracticaTienda();
        instance.setArticulos(articulos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientes method, of class PracticaTienda.
     */
    @Test
    public void testGetClientes() {
        System.out.println("getClientes");
        PracticaTienda instance = new PracticaTienda();
        HashMap<String, Cliente> expResult = null;
        HashMap<String, Cliente> result = instance.getClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientes method, of class PracticaTienda.
     */
    @Test
    public void testSetClientes() {
        System.out.println("setClientes");
        HashMap<String, Cliente> clientes = null;
        PracticaTienda instance = new PracticaTienda();
        instance.setClientes(clientes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PracticaTienda.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PracticaTienda.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargaDatos method, of class PracticaTienda.
     */
    @Test
    public void testCargaDatos() {
        System.out.println("cargaDatos");
        PracticaTienda instance = new PracticaTienda();
        instance.cargaDatos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuExamen1903026 method, of class PracticaTienda.
     */
    @Test
    public void testMenuExamen1903026() {
        System.out.println("menuExamen1903026");
        PracticaTienda instance = new PracticaTienda();
        instance.menuExamen1903026();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of examenUno method, of class PracticaTienda.
     */
    @Test
    public void testExamenUno() {
        System.out.println("examenUno");
        PracticaTienda instance = new PracticaTienda();
        instance.examenUno();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of examenDos method, of class PracticaTienda.
     */
    @Test
    public void testExamenDos() {
        System.out.println("examenDos");
        PracticaTienda instance = new PracticaTienda();
        instance.examenDos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of examenTres method, of class PracticaTienda.
     */
    @Test
    public void testExamenTres() {
        System.out.println("examenTres");
        PracticaTienda instance = new PracticaTienda();
        instance.examenTres();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of examenCuatro method, of class PracticaTienda.
     */
    @Test
    public void testExamenCuatro() {
        System.out.println("examenCuatro");
        PracticaTienda instance = new PracticaTienda();
        instance.examenCuatro();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of examenCinco method, of class PracticaTienda.
     */
    @Test
    public void testExamenCinco() {
        System.out.println("examenCinco");
        PracticaTienda instance = new PracticaTienda();
        instance.examenCinco();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuExamen2002026 method, of class PracticaTienda.
     */
    @Test
    public void testMenuExamen2002026() {
        System.out.println("menuExamen2002026");
        PracticaTienda instance = new PracticaTienda();
        instance.menuExamen2002026();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejercicioUno method, of class PracticaTienda.
     */
    @Test
    public void testEjercicioUno() {
        System.out.println("ejercicioUno");
        PracticaTienda instance = new PracticaTienda();
        instance.ejercicioUno();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejercicioDos method, of class PracticaTienda.
     */
    @Test
    public void testEjercicioDos() {
        System.out.println("ejercicioDos");
        PracticaTienda instance = new PracticaTienda();
        instance.ejercicioDos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejercicioTres method, of class PracticaTienda.
     */
    @Test
    public void testEjercicioTres() {
        System.out.println("ejercicioTres");
        PracticaTienda instance = new PracticaTienda();
        instance.ejercicioTres();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejercicioCuatro method, of class PracticaTienda.
     */
    @Test
    public void testEjercicioCuatro() {
        System.out.println("ejercicioCuatro");
        PracticaTienda instance = new PracticaTienda();
        instance.ejercicioCuatro();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejercicioCinco method, of class PracticaTienda.
     */
    @Test
    public void testEjercicioCinco() {
        System.out.println("ejercicioCinco");
        PracticaTienda instance = new PracticaTienda();
        instance.ejercicioCinco();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuExamen0502026 method, of class PracticaTienda.
     */
    @Test
    public void testMenuExamen0502026() {
        System.out.println("menuExamen0502026");
        PracticaTienda instance = new PracticaTienda();
        instance.menuExamen0502026();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uno method, of class PracticaTienda.
     */
    @Test
    public void testUno() {
        System.out.println("uno");
        PracticaTienda instance = new PracticaTienda();
        instance.uno();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dos method, of class PracticaTienda.
     */
    @Test
    public void testDos() {
        System.out.println("dos");
        PracticaTienda instance = new PracticaTienda();
        instance.dos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tres method, of class PracticaTienda.
     */
    @Test
    public void testTres() {
        System.out.println("tres");
        PracticaTienda instance = new PracticaTienda();
        instance.tres();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cuatro method, of class PracticaTienda.
     */
    @Test
    public void testCuatro() {
        System.out.println("cuatro");
        PracticaTienda instance = new PracticaTienda();
        instance.cuatro();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cinco method, of class PracticaTienda.
     */
    @Test
    public void testCinco() {
        System.out.println("cinco");
        PracticaTienda instance = new PracticaTienda();
        instance.cinco();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuOpciones method, of class PracticaTienda.
     */
    @Test
    public void testMenuOpciones() {
        System.out.println("menuOpciones");
        PracticaTienda instance = new PracticaTienda();
        instance.menuOpciones();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuListados method, of class PracticaTienda.
     */
    @Test
    public void testMenuListados() {
        System.out.println("menuListados");
        PracticaTienda instance = new PracticaTienda();
        instance.menuListados();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listado method, of class PracticaTienda.
     */
    @Test
    public void testListado() {
        System.out.println("listado");
        PracticaTienda instance = new PracticaTienda();
        instance.listado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listadoArticulos method, of class PracticaTienda.
     */
    @Test
    public void testListadoArticulos() {
        System.out.println("listadoArticulos");
        PracticaTienda instance = new PracticaTienda();
        instance.listadoArticulos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listadoClientes method, of class PracticaTienda.
     */
    @Test
    public void testListadoClientes() {
        System.out.println("listadoClientes");
        PracticaTienda instance = new PracticaTienda();
        instance.listadoClientes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listadoPedidos method, of class PracticaTienda.
     */
    @Test
    public void testListadoPedidos() {
        System.out.println("listadoPedidos");
        PracticaTienda instance = new PracticaTienda();
        instance.listadoPedidos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuArticulos method, of class PracticaTienda.
     */
    @Test
    public void testMenuArticulos() {
        System.out.println("menuArticulos");
        PracticaTienda instance = new PracticaTienda();
        instance.menuArticulos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bajaArticulo method, of class PracticaTienda.
     */
    @Test
    public void testBajaArticulo() {
        System.out.println("bajaArticulo");
        PracticaTienda instance = new PracticaTienda();
        instance.bajaArticulo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reposicionArticulos method, of class PracticaTienda.
     */
    @Test
    public void testReposicionArticulos() {
        System.out.println("reposicionArticulos");
        PracticaTienda instance = new PracticaTienda();
        instance.reposicionArticulos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuClientes method, of class PracticaTienda.
     */
    @Test
    public void testMenuClientes() {
        System.out.println("menuClientes");
        PracticaTienda instance = new PracticaTienda();
        instance.menuClientes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of altaCliente method, of class PracticaTienda.
     */
    @Test
    public void testAltaCliente() {
        System.out.println("altaCliente");
        PracticaTienda instance = new PracticaTienda();
        instance.altaCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bajaCliente method, of class PracticaTienda.
     */
    @Test
    public void testBajaCliente() {
        System.out.println("bajaCliente");
        PracticaTienda instance = new PracticaTienda();
        instance.bajaCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarCliente method, of class PracticaTienda.
     */
    @Test
    public void testModificarCliente() {
        System.out.println("modificarCliente");
        PracticaTienda instance = new PracticaTienda();
        instance.modificarCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuPedidos method, of class PracticaTienda.
     */
    @Test
    public void testMenuPedidos() {
        System.out.println("menuPedidos");
        PracticaTienda instance = new PracticaTienda();
        instance.menuPedidos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bajaPedido method, of class PracticaTienda.
     */
    @Test
    public void testBajaPedido() {
        System.out.println("bajaPedido");
        PracticaTienda instance = new PracticaTienda();
        instance.bajaPedido();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stock method, of class PracticaTienda.
     */
    @Test
    public void testStock() throws Exception {
        System.out.println("stock");
        Articulo a = null;
        int unidades = 0;
        PracticaTienda instance = new PracticaTienda();
        instance.stock(a, unidades);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoPedido method, of class PracticaTienda.
     */
    @Test
    public void testNuevoPedido() {
        System.out.println("nuevoPedido");
        PracticaTienda instance = new PracticaTienda();
        instance.nuevoPedido();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalPedido method, of class PracticaTienda.
     */
    @Test
    public void testTotalPedido() {
        System.out.println("totalPedido");
        Pedido p = null;
        PracticaTienda instance = new PracticaTienda();
        double expResult = 0.0;
        double result = instance.totalPedido(p);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalVendido method, of class PracticaTienda.
     */
    @Test
    public void testTotalVendido() {
        System.out.println("totalVendido");
        Articulo a = null;
        PracticaTienda instance = new PracticaTienda();
        int expResult = 0;
        int result = instance.totalVendido(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generaIdPedido method, of class PracticaTienda.
     */
    @Test
    public void testGeneraIdPedido() {
        System.out.println("generaIdPedido");
        String idCliente = "";
        PracticaTienda instance = new PracticaTienda();
        String expResult = "";
        String result = instance.generaIdPedido(idCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuArchivos method, of class PracticaTienda.
     */
    @Test
    public void testMenuArchivos() {
        System.out.println("menuArchivos");
        PracticaTienda instance = new PracticaTienda();
        instance.menuArchivos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of infoArchivo method, of class PracticaTienda.
     */
    @Test
    public void testInfoArchivo() {
        System.out.println("infoArchivo");
        PracticaTienda.infoArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of borrarArchivo method, of class PracticaTienda.
     */
    @Test
    public void testBorrarArchivo() {
        System.out.println("borrarArchivo");
        PracticaTienda.borrarArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cambiarNombreArchivo method, of class PracticaTienda.
     */
    @Test
    public void testCambiarNombreArchivo() {
        System.out.println("cambiarNombreArchivo");
        PracticaTienda.cambiarNombreArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of escribirArchivo method, of class PracticaTienda.
     */
    @Test
    public void testEscribirArchivo() {
        System.out.println("escribirArchivo");
        PracticaTienda.escribirArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerArchivo method, of class PracticaTienda.
     */
    @Test
    public void testLeerArchivo() {
        System.out.println("leerArchivo");
        PracticaTienda.leerArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leeClientes method, of class PracticaTienda.
     */
    @Test
    public void testLeeClientes() {
        System.out.println("leeClientes");
        PracticaTienda instance = new PracticaTienda();
        instance.leeClientes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leeArticulos method, of class PracticaTienda.
     */
    @Test
    public void testLeeArticulos() {
        System.out.println("leeArticulos");
        PracticaTienda instance = new PracticaTienda();
        instance.leeArticulos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuEjercicios method, of class PracticaTienda.
     */
    @Test
    public void testMenuEjercicios() {
        System.out.println("menuEjercicios");
        PracticaTienda instance = new PracticaTienda();
        instance.menuEjercicios();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pruebas method, of class PracticaTienda.
     */
    @Test
    public void testPruebas() {
        System.out.println("pruebas");
        PracticaTienda instance = new PracticaTienda();
        instance.pruebas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalCliente2 method, of class PracticaTienda.
     */
    @Test
    public void testTotalCliente2() {
        System.out.println("totalCliente2");
        Cliente c = null;
        PracticaTienda instance = new PracticaTienda();
        double expResult = 0.0;
        double result = instance.totalCliente2(c);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalPedido2 method, of class PracticaTienda.
     */
    @Test
    public void testTotalPedido2() {
        System.out.println("totalPedido2");
        Pedido p = null;
        PracticaTienda instance = new PracticaTienda();
        double expResult = 0.0;
        double result = instance.totalPedido2(p);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
