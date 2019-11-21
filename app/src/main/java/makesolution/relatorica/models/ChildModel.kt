package makesolution.relatorica.models

import android.os.Bundle
import java.util.*

data class ChildModel( val PadreId: Int,
                       val NombreCompleto: String,
                       val FechaNacimiento: String){
    companion object {
        fun from(bundle: Bundle): ChildModel {
            return ChildModel(
                bundle.getInt("PadreId")!!,
                bundle.getString("NombreCompleto")!!,
                bundle.getString("FechaNacimiento")!!
            )
        }
    }


    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("PadreId",PadreId)
        bundle.putString("NombreCompleto",NombreCompleto)
        bundle.putString("FechaNacimiento",FechaNacimiento)


        return bundle
    }



}