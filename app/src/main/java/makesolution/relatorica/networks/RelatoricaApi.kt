package makesolution.relatorica.networks

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import makesolution.relatorica.models.PruchaseModel
import makesolution.relatorica.responses.LoginResponse
import makesolution.relatorica.responses.PurchaseResponse
import makesolution.relatorica.responses.StoreResponse

class RelatoricaApi {
    companion object{
        val baseUrl = "http://18.188.102.230/Relatorica"
        var historiaUrlGet = "$baseUrl/historiaapi/histories"
        val loginUrlPost = "$baseUrl/loginapi/logins/fathers"
        val buyUrlPost = "$baseUrl/purchasesapi/purchases"

        fun PostCompra(key: String,
                       url: String,
                       padreId: Int,
                       historiaId: Int,
                       fechaCompra : String,
                       costo: Double,
                       responseHandler: (PurchaseResponse?)-> Unit, errorHandler: (ANError?) -> Unit)
        {

            AndroidNetworking.post(url)
                .addHeaders("Authorization",key)
                .addBodyParameter("HistoriaId",historiaId.toString())
                .addBodyParameter("PadreId",padreId.toString())
                .addBodyParameter("FechaCompra",fechaCompra)
                .addBodyParameter("Costo",costo.toString())
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PurchaseResponse::class.java,object : ParsedRequestListener<PurchaseResponse> {
                    override fun onResponse(response: PurchaseResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError) {
                        errorHandler(anError)
                    }
                })
        }

        fun PostLogin(url: String,
                       credenciales: String,
                        contrasenia:String,
                       responseHandler: (LoginResponse?)-> Unit, errorHandler: (ANError?) -> Unit)
        {

            AndroidNetworking.post(url)
                .addBodyParameter("Credenciales",credenciales)
                .addBodyParameter("Contrasenia",contrasenia)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginResponse::class.java,object : ParsedRequestListener<LoginResponse> {
                    override fun onResponse(response: LoginResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError) {
                        errorHandler(anError)
                    }
                })
        }

        fun GetHistoriasNormalOrById(key: String,
                         url: String,
                         responseHandler: (StoreResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(StoreResponse::class.java,
                    object : ParsedRequestListener<StoreResponse> {
                        override fun onResponse(response: StoreResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

    }
}