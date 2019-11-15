package makesolution.relatorica.models

import android.os.Bundle

data class SoundModel (val SonidoId: Int,
                       val Nombre: String,
                       val Url: String,
                       val GeneroId: Int,
                       val Estado: String,
                       val FechaRegistro: String,
                       val UsuarioId: Int){
    companion object{
        fun from(bundle: Bundle): SoundModel{
            return SoundModel(
                bundle.getInt("SonidoId")!!,
                bundle.getString("Nombre")!!,
                bundle.getString("Url")!!,
                bundle.getInt("GeneroId")!!,
                bundle.getString("Estado")!!,
                bundle.getString("FechaRegistro")!!,
                bundle.getInt("UsuarioId")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("SonidoId",SonidoId)
        bundle.putString("Nombre",Nombre)
        bundle.putString("Url",Url)
        bundle.putInt("GeneroId",GeneroId)
        bundle.putString("Estado",Estado)
        bundle.putString("FechaRegistro",FechaRegistro)
        bundle.putInt("UsuarioId",UsuarioId)

        return bundle
    }
}