package consola

import datos.RepositorioMemoria
import kotlinx.coroutines.runBlocking
import servicio.ControlCategorias
import servicio.ControlGastos

fun main() = runBlocking {
    val repositorio = RepositorioMemoria()
    val categorias = ControlCategorias(repositorio)
    val gastos = ControlGastos(repositorio)

    try {
        categorias.crearCategoria("Comida")
        categorias.crearCategoria("Transporte")
        gastos.agregar("Almuerzo", 2500, "Comida", "2026-10-29")

        println("=== Categorías ===")
        categorias.listarCategorias().forEach(::println)

        println("\\n=== Transacciones ===")
        val listaCategorias = categorias.listarCategorias()
        gastos.listar().forEach { t ->
            val nombreCategoria = listaCategorias.firstOrNull { it.id == t.categoria }?.nombre
                ?: "Desconocida"
            println("ID: ${t.id} | ${t.descripcion} | Q${"%.2f".format(t.monto / 100.0)} | " +
                "$nombreCategoria | ${t.fecha}")
        }

        println("\\nBuscar ID 1: ${gastos.buscarPorId(1)}")
        println("Eliminar ID 1: ${gastos.eliminarPorId(1)}")
        println("Transacciones restantes: ${gastos.listar().size}")
    } catch (e: IllegalArgumentException) {
        println("Error de validación: ${e.message}")
    } catch (e: IllegalStateException) {
        println("Operación no permitida: ${e.message}")
    }
}
