package modelo

data class Transaccion(
    val id: Int,
    val descripcion: String,
    val monto: Int, //En Centavos
    val categoria: Int, // referencia a clase categoria
    val fecha: String
)