package datos

import modelo.Categoria
import modelo.Transaccion

/** Implementación temporal en memoria; los datos se pierden al cerrar el programa. */
class RepositorioMemoria : GastosInterface, CategoriaInterface {
    private val categorias = mutableListOf<Categoria>()
    private val transacciones = mutableListOf<Transaccion>()
    private var siguienteCategoriaId = 1
    private var siguienteTransaccionId = 1

    override suspend fun crearCategoria(nombre: String): Int {
        val limpio = nombre.trim()
        require(limpio.isNotEmpty()) { "El nombre de la categoría no puede estar vacío" }
        require(categorias.none { it.nombre.equals(limpio, ignoreCase = true) }) {
            "Ya existe una categoría con ese nombre"
        }
        val categoria = Categoria(siguienteCategoriaId++, limpio, esGasto = true)
        categorias.add(categoria)
        return categoria.id
    }

    override suspend fun listarCategorias(): List<Categoria> = categorias.toList()

    override suspend fun eliminarCategoria(nombre: String): Int {
        val categoria = categorias.firstOrNull {
            it.nombre.equals(nombre.trim(), ignoreCase = true)
        } ?: return 0
        check(transacciones.none { it.categoria == categoria.id }) {
            "No puedes eliminar una categoría que tiene transacciones"
        }
        categorias.remove(categoria)
        return 1
    }

    override suspend fun agregar(gasto: Transaccion): Int {
        require(categorias.any { it.id == gasto.categoria }) { "La categoría indicada no existe" }
        val nuevo = gasto.copy(id = siguienteTransaccionId++)
        transacciones.add(nuevo)
        return nuevo.id
    }

    override suspend fun listar(): List<Transaccion> = transacciones.toList()

    override suspend fun buscarPorId(id: Int): Transaccion? =
        transacciones.firstOrNull { it.id == id }

    override suspend fun eliminarPorId(id: Int): Boolean {
        val encontrada = transacciones.firstOrNull { it.id == id } ?: return false
        transacciones.remove(encontrada)
        return true
    }

    override suspend fun obtenerCategoria(nombre: String): Int =
        categorias.firstOrNull { it.nombre.equals(nombre.trim(), ignoreCase = true) }?.id
            ?: throw IllegalArgumentException("No existe la categoría '$nombre'")
}
