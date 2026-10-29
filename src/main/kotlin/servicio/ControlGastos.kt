package servicio

import datos.GastosInterface
import modelo.Transaccion

class ControlGastos(private val controlador: GastosInterface) {

    suspend fun agregar(descripcion: String, monto: Int, categoria: String, fecha: String): Int {
        // Validaciones
        if (descripcion.isBlank() || categoria.isBlank() || fecha.isBlank())
            throw IllegalArgumentException("Los campos no deben estar en blanco")
        if (monto < 1)
            throw IllegalArgumentException("El monto deber ser positivo")
        if (fecha.matches(Regex("""\d{4}-\d{2}-\d{2}""")))
            throw IllegalArgumentException("La fecha no coincide con el formato requerido")

        return controlador.agregar(Transaccion(0,descripcion,monto,controlador.obtenerCategoria(categoria),fecha))
    }

    suspend fun listar(): List<Transaccion>{
        return controlador.listar()
    }

    suspend fun buscarPorId(id: Int): Transaccion?{
        return controlador.buscarPorId(id)
    }

    suspend fun eliminarPorId(id: Int): Boolean{
        return controlador.eliminarPorId(id)
    }

}