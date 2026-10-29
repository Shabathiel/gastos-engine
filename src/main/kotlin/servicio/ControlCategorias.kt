package servicio

import datos.CategoriaInterface
import datos.GastosInterface
import modelo.Categoria

class ControlCategorias (private val controlador: CategoriaInterface){

    suspend fun crearCategoria(nombre: String): Int{
        return controlador.crearCategoria(nombre)
    }

    suspend fun listarCategorias(): List<Categoria>{
        return controlador.listarCategorias()
    }

    suspend fun eliminarCategoria(nombre: String): Int{
        return controlador.eliminarCategoria(nombre)
    }
}