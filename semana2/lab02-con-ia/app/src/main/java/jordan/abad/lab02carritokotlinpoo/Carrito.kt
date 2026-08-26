package jordan.abad.lab02carritokotlinpoo

class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
) {
    fun calcularImporte(): Double {
        return precio * cantidad
    }
}

abstract class ComponenteFinanciero {
    abstract fun calcular(base: Double): Double
}

class CalculadoraIGV : ComponenteFinanciero() {
    override fun calcular(base: Double): Double {
        return base * 0.18
    }
}

// Otra clase que hereda de la misma clase abstracta, con su propia logica
class CalculadoraDescuento : ComponenteFinanciero() {
    override fun calcular(base: Double): Double {
        return when {
            base > 5000 -> base * 0.10
            base > 3000 -> base * 0.05
            else -> 0.0
        }
    }
}

class Carrito(private val cliente: String) {

    private val productos = mutableListOf<Producto>()
    private val calculadoraIGV = CalculadoraIGV()
    private val calculadoraDescuento = CalculadoraDescuento()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
        println("Producto agregado: ${producto.nombre}")
    }

    fun obtenerProductos(): List<Producto> {
        return productos
    }

    fun obtenerCliente(): String {
        return cliente
    }

    fun obtenerCantidadProductos(): Int {
        return productos.size
    }

    fun calcularSubtotal(): Double {
        var subtotal = 0.0
        for (p in productos) {
            subtotal += p.calcularImporte()
        }
        return subtotal
    }

    fun calcularIGV(): Double {
        return calculadoraIGV.calcular(calcularSubtotal())
    }

    fun calcularTotal(): Double {
        return calcularSubtotal() + calcularIGV()
    }

    fun calcularDescuento(): Double {
        return calculadoraDescuento.calcular(calcularTotal())
    }

    fun calcularTotalConDescuento(): Double {
        return calcularTotal() - calcularDescuento()
    }

    fun obtenerProductoMasCaro(): Producto? {
        return productos.maxByOrNull { it.precio }
    }

    fun mostrarDetalle() {
        println("---------- DETALLE DEL CARRITO ----------")
        var i = 1
        for (p in productos) {
            println(
                String.format(
                    "%d. %-20s x%d  S/ %8.2f",
                    i, p.nombre, p.cantidad, p.calcularImporte()
                )
            )
            i++
        }
        println("-------------------------------------------")
    }
}

fun main() {
    println("=========================================")
    println("   CARRITO DE COMPRAS - TIENDA TECSUP   ")
    println("=========================================")

    val carrito = Carrito("Jordan Abad Mejia")

    println("Cliente: ${carrito.obtenerCliente()}")
    println()

    carrito.agregarProducto(Producto("Laptop HP", 2500.0, 1))
    carrito.agregarProducto(Producto("Mouse Logitech", 45.5, 2))
    carrito.agregarProducto(Producto("Audifonos Sony", 120.0, 1))
    carrito.agregarProducto(Producto("USB Kingston 64GB", 25.0, 3))
    println()

    carrito.mostrarDetalle()
    println("Cantidad de productos : ${carrito.obtenerCantidadProductos()}")

    val subtotal = carrito.calcularSubtotal()
    val igv = carrito.calcularIGV()
    val total = carrito.calcularTotal()

    println("Subtotal     : S/ " + "%.2f".format(subtotal))
    println("IGV (18%)    : S/ " + "%.2f".format(igv))
    println("TOTAL A PAGAR: S/ " + "%.2f".format(total))
    println("-------------------------------------------")

    val masCaro = carrito.obtenerProductoMasCaro()
    if (masCaro != null) {
        println("Producto mas caro: ${masCaro.nombre} " + "(S/ %.2f)".format(masCaro.precio))
    }

    val descuento = carrito.calcularDescuento()
    val totalConDescuento = carrito.calcularTotalConDescuento()

    if (descuento > 0) {
        val porcentaje = if (total > 5000) 10 else 5
        val umbral = if (total > 5000) 5000 else 3000
        println("Descuento aplicado: $porcentaje% por compra mayor a S/ $umbral")
    } else {
        println("Descuento aplicado: Ninguno")
    }
    println("TOTAL CON DESCUENTO    : S/ " + "%.2f".format(totalConDescuento))
    println()
    println("Gracias por su compra, ${carrito.obtenerCliente()}!")
}