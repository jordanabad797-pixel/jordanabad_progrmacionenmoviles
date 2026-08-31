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

    fun mostrarReporteAtraso() {
        val diasAtraso = calcularDiasAtraso()
        if (diasAtraso == 0L) {
            println("No hay dias de atraso que reportar.")
            return
        }

        println(String.format("%-4s %-12s %-12s %-12s", "Dia", "Fecha", "Multa/Dia", "Acumulado"))
        var acumulado = 0.0
        val multaDiaria = usuario.obtenerMultaDiaria()

        for (i in 1..diasAtraso) {
            val fechaDelDia = fechaDevolucion.plusDays(i)
            acumulado += multaDiaria
            println(
                String.format(
                    "%-4d %-12s S/ %-9.2f S/ %-9.2f",
                    i, fechaDelDia, multaDiaria, acumulado
                )
            )
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
    println()

    libro.mostrarReporteAtraso()
    println()

    println("Multa total: S/ " + "%.2f".format(libro.calcularMultaTotal()))
}