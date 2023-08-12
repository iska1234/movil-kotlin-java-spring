package com.example.sispagosdef

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testLoginValido() {
        val usuario = "usuario1"
        val contrasena = "contrasena1"
        assertTrue(login(usuario, contrasena))
    }

    @Test
    fun testLoginInvalido() {
        val usuario = "usuario2"
        val contrasena = "contrasena2"
        assertFalse(login(usuario, contrasena))
    }

    fun login(usuario: String, contrasena: String): Boolean {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            return false
        }

        if (usuario == "usuario1" && contrasena == "contrasena1") {
            return true
        }
        return false
    }

//    fun obtenerTotal(numeros: Set<Int>): Int {
//        var total = 0
//        for (numero in numeros) {
//            total += numero
//        }
//        return total
//    }
//
//    @Test
//    fun testTotal() {
//        val numeros = setOf(1, 2, 3, 4, 5)
//        assertEquals(15, obtenerTotal(numeros))
//    }
//
//    @Test
//    fun testTotalConCeros() {
//        val numeros = setOf(0, 1, 2, 3, 4, 5)
//        assertEquals(15, obtenerTotal(numeros))
//    }
//
//    @Test
//    fun testTotalConNumerosNegativos() {
//        val numeros = setOf(-1, 2, -3, 4, -5)
//        assertEquals(-3, obtenerTotal(numeros))
//    }
//
//    @Test
//    fun testTotalConConjuntoVacio() {
//        val numeros = emptySet<Int>()
//        assertEquals(0, obtenerTotal(numeros))
//    }

    fun esEntero(cadena: String): Boolean {
        return try {
            cadena.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    @Test
    fun testEsEnteroConEntero() {
        assertEquals(true, esEntero("123"))
    }

    @Test
    fun testEsEnteroConDecimal() {
        assertEquals(false, esEntero("123.45"))
    }

    @Test
    fun testEsEnteroConTexto() {
        assertEquals(false, esEntero("abc"))
    }

    @Test
    fun testEsEnteroConSigno() {
        assertEquals(true, esEntero("-123"))
    }

    @Test
    fun testEsEnteroConPalabraVacia() {
        assertEquals(false, esEntero(""))
    }

}