package makesolution.relatorica.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.android.synthetic.main.dialog_purchase.*
import makesolution.relatorica.R
import makesolution.relatorica.models.StoreModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.PurchaseHistoryResponse


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

            RelatoricaApi.PostCompra(token,url,usuarioId,historiaId,"09/18/2019",costo,
                { response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }
    private fun handleResponse(historyResponse: PurchaseHistoryResponse?){
        if(true.equals(historyResponse!!.Error)){
            Log.d("Respuesta Falsa", historyResponse!!.Message)
            return
        }
        //Toast.makeText(this, "Comprra realizada satisfactoriamente.", Toast.LENGTH_SHORT).show()
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_purchase)
        val continueButton=dialog.findViewById<Button>(R.id.continueButton)
        continueButton.setOnClickListener {
            dialog.dismiss()
            val intento = Intent(this, MainActivity::class.java)
            startActivity(intento)
        }
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
