package makesolution.relatorica.activities

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_login.*
import makesolution.relatorica.R
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.LoginResponse

class LoginActivity : AppCompatActivity() {

    private val STRING_PREFERENCE = "Session"
    private val TOKEN = "Token"
    private val NOMBREPROVIDER = "NombreProvider"
    private val PROVIDERID = "ProviderId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var url: String = RelatoricaApi.loginUrlPost

        var usernameET = userEditText.text.toString()
        var passwordET = passwordEditText.text.toString()

        RelatoricaApi.PostLogin(url,usernameET,passwordET,
            { response -> handleResponse(response) },
            { error -> handleError(error)})
    }
    private fun handleResponse(response: LoginResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        var preferences : SharedPreferences = this.getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE)

        val sp = preferences.edit()
        sp.putString(TOKEN, response.Data!!.Token)
        sp.putString(NOMBREPROVIDER, response.Data!!.Nombre)
        sp.putInt(PROVIDERID, response.Data!!.ProviderId)
        sp.apply()

        if (preferences.getBoolean(STRING_PREFERENCE, false)) {
            val localIntent = Intent(this, LocalActivity::class.java)
            startActivity(localIntent)
            finish()
        }

        val intento = Intent(this, LocalActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
