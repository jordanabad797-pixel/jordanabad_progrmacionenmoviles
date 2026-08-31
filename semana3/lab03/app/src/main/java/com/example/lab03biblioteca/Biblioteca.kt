package com.example.biblioteca

import java.time.LocalDate
import java.time.temporal.ChronoUnit

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
) {
    fun calcularDiasAtraso(): Long {
        val entrega = fechaEntrega ?: return 0
        val dias = ChronoUnit.DAYS.between(fechaDevolucion, entrega)
        return if (dias > 0) dias else 0
    }

    fun calcularMultaTotal(): Double {
        return calcularDiasAtraso() * usuario.obtenerMultaDiaria()
    }

    fun obtenerEstado(): String {
        return when {
            fechaEntrega == null -> "No entregado"
            calcularDiasAtraso() > 0 -> "Devuelto con ${calcularDiasAtraso()} dias de atraso"
            else -> "Entregado"
        }
    }
}

fun main() {
    println("=========================================")
    println("   SISTEMA DE PRESTAMO DE LIBROS - TECSUP   ")
    println("=========================================")

    val alumno = Alumno("Jordan Abad Mejia")
    val libro = Libro(
        titulo = "Kotlin para principiantes",
        usuario = alumno,
        fechaPrestamo = LocalDate.of(2026, 10, 14),
        fechaDevolucion = LocalDate.of(2026, 10, 17),
        fechaEntrega = LocalDate.of(2026, 10, 21)
    )

    println("Titulo: ${libro.titulo}")
    println("Usuario: ${libro.usuario.nombre} (${libro.usuario.obtenerTipo()})")
    println("Fecha prestamo: ${libro.fechaPrestamo}")
    println("Fecha devolucion: ${libro.fechaDevolucion}")
    println("Fecha entrega: ${libro.fechaEntrega}")
    println("Estado: ${libro.obtenerEstado()}")
    println("Dias de atraso: ${libro.calcularDiasAtraso()}")
    println("Multa diaria: S/ " + "%.2f".format(libro.usuario.obtenerMultaDiaria()))
    println("Multa total: S/ " + "%.2f".format(libro.calcularMultaTotal()))
}