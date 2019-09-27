package makesolution.relatorica.networks

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import makesolution.relatorica.responses.GeneralResponse

class RelatoricaApi {
    companion object{
        val baseUrl = "http://18.188.102.230/Relatorica"
        var historiaUrlGet = "$baseUrl/historiaapi/histories"

        fun PostCompra(key: String, url: String,
                               customerId: Int,
                               responseHandler: (GeneralResponse?)-> Unit, errorHandler: (ANError?) -> Unit)
        {

            AndroidNetworking.post(url)
                .addHeaders("Authorization",key)
                .addBodyParameter("prueba",customerId.toString())
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(GeneralResponse::class.java,object : ParsedRequestListener<GeneralResponse> {
                    override fun onResponse(response: GeneralResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError) {
                        errorHandler(anError)
                    }
                })
        }

        fun GetHistorias(key: String, url: String, responseHandler: (GeneralResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(GeneralResponse::class.java,
                    object : ParsedRequestListener<GeneralResponse> {
                        override fun onResponse(response: GeneralResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

    }
}