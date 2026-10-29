package datos

import modelo.Categoria

interface CategoriaInterface {
    suspend fun crearCategoria(nombre: String): Int

    suspend fun listarCategorias(): List<Categoria>

    suspend fun eliminarCategoria(nombre: String): Int
}