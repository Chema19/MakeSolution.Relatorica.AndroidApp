package makesolution.relatorica.models

import android.os.Bundle

data class PurchaseModel (val CompraId: Int,
                          val PadreId: Int,
                          val HistoriaId: Int,
                          val FechaCompra: String,
                          val Estado: String,
                          val Costo: Double) {
    companion object{
        fun from(bundle: Bundle): PurchaseModel{
            return PurchaseModel(
                bundle.getInt("CompraId")!!,
                bundle.getInt("PadreId")!!,
                bundle.getInt("HistoriaId")!!,
                bundle.getString("FechaCompra")!!,
                bundle.getString("Estado")!!,
                bundle.getDouble("Costo")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("CompraId",HistoriaId)
        bundle.putInt("PadreId",PadreId)
        bundle.putInt("HistoriaId",HistoriaId)
        bundle.putString("FechaCompra",FechaCompra)
        bundle.putString("Estado",Estado)
        bundle.putDouble("Costo",Costo)

        return bundle
    }
}