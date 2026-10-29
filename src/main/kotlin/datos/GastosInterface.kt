package datos

import modelo.Transaccion

interface GastosInterface {
    suspend fun agregar(gasto: Transaccion) : Int

    suspend fun listar(): List<Transaccion>

    suspend fun buscarPorId(id: Int): Transaccion?

    suspend fun eliminarPorId(id: Int): Boolean

    fun obtenerCategoria(nombre: String): Int
}