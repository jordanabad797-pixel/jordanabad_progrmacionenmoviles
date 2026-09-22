package com.abad.lab04carrito

data class Producto(
    val nombre: String,
    val precio: Double,
    val cantidad: Int
) {
    val subtotal: Double
        get() = precio * cantidad
}