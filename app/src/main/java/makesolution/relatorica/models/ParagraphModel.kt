package makesolution.relatorica.models

import android.os.Bundle

data class ParagraphModel(val ParrafoId: Int,
                          val Texto: String,
                          val Orden: Int,
                          val Estado: String,
                          val FechaRegistro: String,
                          val HistoriaId: Int,
                          val SonidoId: Int) {

    companion object{
        fun from(bundle: Bundle): ParagraphModel{
            return ParagraphModel(
                bundle.getInt("ParrafoId")!!,
                bundle.getString("Texto")!!,
                bundle.getInt("Orden")!!,
                bundle.getString("Estado")!!,
                bundle.getString("FechaRegistro")!!,
                bundle.getInt("HistoriaId")!!,
                bundle.getInt("SonidoId")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("ParrafoId",ParrafoId)
        bundle.putString("Texto",Texto)
        bundle.putInt("Orden",Orden)
        bundle.putString("Estado",Estado)
        bundle.putString("FechaRegistro",FechaRegistro)
        bundle.putInt("HistoriaId",HistoriaId)
        bundle.putInt("SonidoId",SonidoId)

        return bundle
    }
}