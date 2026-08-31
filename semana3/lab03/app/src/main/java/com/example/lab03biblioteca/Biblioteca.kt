package com.example.biblioteca

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        val dias = java.time.temporal.ChronoUnit.DAYS.between(fechaDevolucion, entrega)
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

val formatoFecha: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun leerTexto(mensaje: String): String {
    print(mensaje)
    return readLine()?.trim() ?: ""
}

fun leerFecha(mensaje: String): LocalDate {
    while (true) {
        print(mensaje)
        val entrada = readLine()?.trim() ?: ""
        try {
            return LocalDate.parse(entrada, formatoFecha)
        } catch (e: Exception) {
            println("Formato invalido. Usa dd/MM/yyyy, ejemplo: 14/10/2026")
        }
    }
}

fun leerTipoUsuario(): Int {
    while (true) {
        println("Tipo de usuario:")
        println("1. Docente")
        println("2. Alumno")
        print("Elige una opcion (1 o 2): ")
        val entrada = readLine()?.trim() ?: ""
        if (entrada == "1" || entrada == "2") {
            return entrada.toInt()
        }
        println("Opcion invalida, intenta de nuevo.")
    }
}

fun main() {
    println("=========================================")
    println("   SISTEMA DE PRESTAMO DE LIBROS - TECSUP   ")
    println("=========================================")

    val titulo = leerTexto("Titulo del libro: ")
    val nombreUsuario = leerTexto("Nombre del usuario: ")
    val opcionTipo = leerTipoUsuario()

    val usuario: Usuario = if (opcionTipo == 1) {
        Docente(nombreUsuario)
    } else {
        Alumno(nombreUsuario)
    }

    val fechaPrestamo = leerFecha("Fecha de prestamo (dd/MM/yyyy): ")
    val fechaDevolucion = leerFecha("Fecha de devolucion pactada (dd/MM/yyyy): ")

    val entregado = leerTexto("Ya se entrego el libro? (si/no): ").lowercase()
    val fechaEntrega: LocalDate? = if (entregado == "si") {
        leerFecha("Fecha real de entrega (dd/MM/yyyy): ")
    } else {
        null
    }

    val libro = Libro(titulo, usuario, fechaPrestamo, fechaDevolucion, fechaEntrega)

    println()
    println("---------- DATOS DEL PRESTAMO ----------")
    println("Titulo: ${libro.titulo}")
    println("Usuario: ${libro.usuario.nombre} (${libro.usuario.obtenerTipo()})")
    println("Fecha prestamo: ${libro.fechaPrestamo.format(formatoFecha)}")
    println("Fecha devolucion: ${libro.fechaDevolucion.format(formatoFecha)}")
    println("Fecha entrega: ${libro.fechaEntrega?.format(formatoFecha) ?: "No entregado"}")
    println("Estado: ${libro.obtenerEstado()}")
    println()

    libro.mostrarReporteAtraso()
    println()

    println("Multa total: S/ " + "%.2f".format(libro.calcularMultaTotal()))
}