package makesolution.relatorica.models

import android.os.Bundle

data class FatherModel(val PadreId: Int,
                       val Nombres: String,
                       val Apellidos: String,
                       val Credenciales: String,
                       val Contrasenia: String,
                       val Correo: String,
                       val Celular: String) {
    companion object{
        fun from(bundle: Bundle): FatherModel{
            return FatherModel(
                bundle.getInt("PadreId")!!,
                bundle.getString("Nombres")!!,
                bundle.getString("Apellidos")!!,
                bundle.getString("Credenciales")!!,
                bundle.getString("Contrasenia")!!,
                bundle.getString("Correo")!!,
                bundle.getString("Celular")!!
            )
        }
    }

    fun toBundle() : Bundle {
        var bundle = Bundle()

        bundle.putInt("PadreId",PadreId)
        bundle.putString("Nombres",Nombres)
        bundle.putString("Apellidos",Apellidos)
        bundle.putString("Credenciales",Credenciales)
        bundle.putString("Contrasenia",Contrasenia)
        bundle.putString("Correo",Correo)
        bundle.putString("Celular",Celular)

        return bundle
    }
}