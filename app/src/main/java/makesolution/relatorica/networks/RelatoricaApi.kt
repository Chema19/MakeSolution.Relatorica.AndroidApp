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
        val fatherUrlPost = "$baseUrl/loginapi/fathers"

        fun getHistory(historyId:Int):String{
            return "${RelatoricaApi.historiaUrlGet}/$historyId"
        }


        fun getPurchasesByFather(padreId:Int):String{
            return "${baseUrl}/purchasesapi/fathers/$padreId/purchases"
        }

        fun getFatherProfile(padreId: Int): String{
            return "${baseUrl}/fatherapi/fathers/$padreId"
        }

        fun getParagraph(historiaId: Int): String{
            return "${baseUrl}/paragraphsapi/histories/$historiaId/paragraphs"
        }

        fun getSonidoById(sonidoId: Int): String{
            return "${baseUrl}/soundsapi/sounds/$sonidoId"
        }

        fun getChildByFather(fatherId: Int): String{
            return "${baseUrl}/fatherapi/fathers/$fatherId"
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

        fun PostFather(url: String,
                       nombres:String,
                       apellidos:String,
                       credenciales:String,
                       contrasenia:String,
                       correo:String,
                       celular:String,
                       responseHandler: (FatherResponse?)-> Unit, errorHandler: (ANError?) -> Unit)
        {

            AndroidNetworking.post(url)
                .addBodyParameter("Nombres",nombres)
                .addBodyParameter("Apellidos",apellidos)
                .addBodyParameter("Credenciales",credenciales)
                .addBodyParameter("Contrasenia",contrasenia)
                .addBodyParameter("Correo",correo)
                .addBodyParameter("Celular",celular)
                .addBodyParameter("Estado","ACT")
                .addBodyParameter("FechaNacimiento","09/18/2019")
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(FatherResponse::class.java,object : ParsedRequestListener<FatherResponse> {
                    override fun onResponse(response: FatherResponse?) {
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

        fun GetFatherById(key: String,
                            url: String,
                            responseHandler: (FatherProfileResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(FatherProfileResponse::class.java,
                    object : ParsedRequestListener<FatherProfileResponse> {
                        override fun onResponse(response: FatherProfileResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun GetParagraphByHistory(key: String,
                          url: String,
                          responseHandler: (ParagraphResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(ParagraphResponse::class.java,
                    object : ParsedRequestListener<ParagraphResponse> {
                        override fun onResponse(response: ParagraphResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun GetSoundById(key: String,
                         url: String,
                         responseHandler: (SoundResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(SoundResponse::class.java,
                    object : ParsedRequestListener<SoundResponse> {
                        override fun onResponse(response: SoundResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }


        fun GetChildByFather(key: String,
                          url: String,
                          responseHandler: (ChildResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("RelatoricaApp")
                .build()
                .getAsObject(ChildResponse::class.java,
                    object : ParsedRequestListener<ChildResponse> {
                        override fun onResponse(response: ChildResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }


    }
}