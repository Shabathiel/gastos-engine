package datos

import modelo.Transaccion

interface GastosRepository {
    suspend fun agregar(gasto: Transaccion) : Int

    suspend fun listar(): List<Transaccion>

    suspend fun buscarPorId(id: Int): Transaccion?

    suspend fun eliminarPorIDD(id: Int): Boolean
}