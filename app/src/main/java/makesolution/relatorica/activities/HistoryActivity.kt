package makesolution.relatorica.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.content_store.*
import makesolution.relatorica.R
import makesolution.relatorica.models.StoreModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.PurchaseResponse


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        val store = StoreModel.from(intent.extras)

        pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher)
        pictureImageView.setErrorImageResId(R.mipmap.ic_launcher)
        pictureImageView.setImageUrl(store.Imagen)
        nameTextView.text = store.Nombre
        priceTextView.text = "Precio : S/. " + store.Precio
        argumentTextView.text = store.Argumento
        var historiaId = store.HistoriaId;
        var costo = store.Precio
        var dato = store.FechaRegistro.split("T")[0]
        dateTextView.text = "Fecha de disponibilidad : " + store.FechaRegistro.split("T")[0]

        val result = getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var usuarioId = result.getInt(getString(R.string.personid), 0)

        buyButton.setOnClickListener{
            var url: String = RelatoricaApi.buyUrlPost

            RelatoricaApi.PostCompra(token,url,historiaId,usuarioId,"09/18/2019",costo,
                { response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }
    private fun handleResponse(response: PurchaseResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
