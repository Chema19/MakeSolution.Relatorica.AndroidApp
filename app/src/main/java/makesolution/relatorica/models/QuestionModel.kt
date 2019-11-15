package makesolution.relatorica.models

import android.os.Bundle

data class QuestionModel(val PreguntaId:Int,
                    val Enunciado:String,
                    val FechaRegistro:String,
                    val Estado:String,
                    val HitoriaId:Int) {
    companion object{
        fun from(bundle: Bundle): QuestionModel{
            return QuestionModel(
                bundle.getInt("PreguntaId")!!,
                bundle.getString("Enunciado")!!,
                bundle.getString("FechaRegistro")!!,
                bundle.getString("Estado")!!,
                bundle.getInt("HistoriaId")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("PreguntaId",PreguntaId)
        bundle.putString("Enunciado",Enunciado)
        bundle.putString("FechaRegistro",FechaRegistro)
        bundle.putString("Estado",Estado)
        bundle.putInt("HistoriaId",HitoriaId)

        return bundle
    }
}