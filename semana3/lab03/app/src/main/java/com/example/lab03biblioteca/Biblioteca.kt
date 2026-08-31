package com.example.biblioteca

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Clase abstracta: define el comportamiento general de un usuario,
// pero cada tipo de usuario (Docente/Alumno) define su propia multa diaria
abstract class Usuario(val nombre: String) {
    abstract fun obtenerMultaDiaria(): Double
    abstract fun obtenerTipo(): String
}

class Docente(nombre: String) : Usuario(nombre) {
    override fun obtenerMultaDiaria(): Double {
        return 3.0
    }

    override fun obtenerTipo(): String {
        return "Docente"
    }
}

class Alumno(nombre: String) : Usuario(nombre) {
    override fun obtenerMultaDiaria(): Double {
        return 1.5
    }

    override fun obtenerTipo(): String {
        return "Alumno"
    }
}

class Libro(
    val titulo: String,
    val usuario: Usuario,
    val fechaPrestamo: LocalDate,
    val fechaDevolucion: LocalDate,
    var fechaEntrega: LocalDate? = null
)

fun main() {
    println("=========================================")
    println("   SISTEMA DE PRESTAMO DE LIBROS - TECSUP   ")
    println("=========================================")
}