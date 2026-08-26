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

class Carrito(private val cliente: String) {

    private val productos = mutableListOf<Producto>()
    private val calculadoraIGV = CalculadoraIGV()

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

    // Método propio de la clase Carrito: muestra el detalle con columnas alineadas
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
}