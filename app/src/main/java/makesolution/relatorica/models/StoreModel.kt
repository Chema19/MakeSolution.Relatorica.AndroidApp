package makesolution.relatorica.models

import android.os.Bundle
import java.util.*

data class StoreModel (
    val HistoriaId: Int,
    val Nombre: String,
    val UsuarioId: Int,
    val NombreUsuario: String,
    val Argumento: String,
    val FechaRegistro: String,
    val Estado: String,
    val Precio: Double,
    val Editorial: String,
    val Imagen: String)
{

    companion object{
        fun from(bundle: Bundle): StoreModel{
            return StoreModel(
                bundle.getInt("HistoriaId")!!,
                bundle.getString("Nombre")!!,
                bundle.getInt("UsuarioId")!!,
                bundle.getString("NombreUsuario")!!,
                bundle.getString("Argumento")!!,
                bundle.getString("FechaRegistro")!!,
                bundle.getString("Estado")!!,
                bundle.getDouble("Precio")!!,
                bundle.getString("Editorial")!!,
                bundle.getString("Imagen")!!
            )
        }
    }

    fun toBundle() : Bundle{
        var bundle = Bundle()

        bundle.putInt("HistoriaId",HistoriaId)
        bundle.putString("Nombre",Nombre)
        bundle.putInt("UsuarioId",UsuarioId)
        bundle.putString("NombreUsuario",NombreUsuario)
        bundle.putString("Argumento",Argumento)
        bundle.putString("FechaRegistro",FechaRegistro)
        bundle.putString("Estado",Estado)
        bundle.putDouble("Precio",Precio)
        bundle.putString("Editorial",Editorial)
        bundle.putString("Imagen",Imagen)

        return bundle
    }

}