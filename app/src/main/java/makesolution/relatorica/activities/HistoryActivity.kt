package makesolution.relatorica.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.content_store.*
import makesolution.relatorica.R
import makesolution.relatorica.models.StoreModel

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
        var dato = store.FechaRegistro.split("T")[0]
        dateTextView.text = "Fecha de disponibilidad : " + store.FechaRegistro.split("T")[0]
    }
}
