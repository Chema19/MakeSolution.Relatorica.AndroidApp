package makesolution.relatorica.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_profile.*
import makesolution.relatorica.R
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.FatherResponse


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        registerProfileButton.setOnClickListener {
            var url: String = RelatoricaApi.fatherUrlPost
            var nombreET = nombreEditText.text.toString()
            var apellidoET = apellidoEditText.text.toString()
            var credencialesET = credencialesEditText.text.toString()
            var constraseniaET = contraseniaEditText.text.toString()
            var correoET = correoEditText.text.toString()
            var celularET = celularEditText.text.toString()


            RelatoricaApi.PostFather(url, nombreET,
                apellidoET,credencialesET,
                constraseniaET,correoET,celularET,
                { response -> handleResponse(response) },
                { error -> handleError(error) })


        }
    }
    private fun handleResponse(Response: FatherResponse?){
        if(true.equals(Response!!.Error)){
            Log.d("Respuesta Falsa", Response!!.Message)
            return
        }
        Toast.makeText(this, "Registro de usuario satisfactorio.", Toast.LENGTH_SHORT).show()
        val intento = Intent(this, LoginActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
