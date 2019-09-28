package makesolution.relatorica.networks

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import makesolution.relatorica.responses.*

class RelatoricaApi {
    companion object{
        val baseUrl = "http://18.188.102.230/Relatorica"
        var historiaUrlGet = "$baseUrl/historiaapi/histories"
        val loginUrlPost = "$baseUrl/loginapi/logins/fathers"
        val buyUrlPost = "$baseUrl/purchasesapi/purchases"

        fun getHistory(historyId:Int):String{
            return "${RelatoricaApi.historiaUrlGet}/$historyId"
        }

        //POST
        fun PostCompra(key: String,
                       url: String,
                       padreId: Int,
                       historiaId: Int,
                       fechaCompra: String,
                       costo: Double,
                       responseHandler: (PurchaseHistoryResponse?)-> Unit,
                       errorHandler: (ANError?) -> Unit)
        {

            AndroidNetworking.post(url)
                .addHeaders("Authorization",key)
                .addBodyParameter("PadreId",padreId.toString())
                .addBodyParameter("HistoriaId",historiaId.toString())
                .addBodyParameter("FechaCompra",fechaCompra)
                .addBodyParameter("Costo",costo.toString())
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PurchaseHistoryResponse::class.java,object : ParsedRequestListener<PurchaseHistoryResponse> {
                    override fun onResponse(historyResponse: PurchaseHistoryResponse?) {
                        responseHandler(historyResponse)
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

        //GET
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

        fun GetHistoriaById(key: String,
                            url: String,
                            responseHandler: (HistoryResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(HistoryResponse::class.java,
                    object : ParsedRequestListener<HistoryResponse> {
                        override fun onResponse(response: HistoryResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun GetCompra(key: String,
                                     url: String,
                                     responseHandler: (PurchaseResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(PurchaseResponse::class.java,
                    object : ParsedRequestListener<PurchaseResponse> {
                        override fun onResponse(response: PurchaseResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

    }
}