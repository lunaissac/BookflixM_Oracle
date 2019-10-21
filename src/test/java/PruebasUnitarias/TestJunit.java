/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import controller.AutorC;
import dao.Conexion;
import java.util.List;
import model.Autor;
import model.Categoria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ISSAC
 */
public class TestJunit {

    public TestJunit() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ConexionBD() throws Exception {
        Conexion resultado = new Conexion();
        int valorEsperado = 1;
        if (resultado.conectar() != null) {
            int conectado = 1;
            assertEquals(valorEsperado, conectado);
        } else if (resultado.conectar() == null) {
            int conectado = 2;
            assertEquals(valorEsperado, conectado);
        }
    }
    
    @Test(expected = Exception.class)
    public void RetornarException() throws Exception {
        AutorC object = new AutorC();
        object.listar();
    }

    @Test
    public void ClaseActivo() throws Exception {
        AutorC object = new AutorC();
        assertNotNull("Esta class no retorna valor",object);
    }

    @Test
    public void TipoVariable() throws Exception {
        Autor object1 = new Autor();
        Categoria object2 = new Categoria();
        assertSame("Este objeto no es igual", object1.getCODAUT(),object2.getCODCAT());
    }

}
