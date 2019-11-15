package makesolution.relatorica.models

import android.os.Bundle

data class ChildModel(    val HijoId:Int,
                          val NombreCompleto:String, val Estado:String,
                          val FechaRegistro:String,val FechaNacimiento:String,val PadreId:Int) {
    companion object{
        fun from (bundle:Bundle): ChildModel{
            return ChildModel(bundle.getInt("HijoId")!!,
                bundle.getString("NombreCompleto")!!,
                bundle.getString("Estado")!!,
                        bundle.getString("FechaRegistro")!!,
                        bundle.getString("FechaNacimiento")!!,
                        bundle.getInt("PadreId")!!
                )
        }
    }
    fun toBundle(): Bundle{
        var bundle = Bundle()
        bundle.putInt("HijoId",HijoId)
        bundle.putString("NombreCompleto",NombreCompleto)
        bundle.putString("Estado",Estado)
        bundle.putString("FechaRegistro",FechaRegistro)
        bundle.putString("FechaNacimiento",FechaNacimiento)
        bundle.putInt("PadreId",PadreId)
        return bundle
    }
}