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

class Carrito(private val cliente: String) {

    private val productos = mutableListOf<Producto>()

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
}