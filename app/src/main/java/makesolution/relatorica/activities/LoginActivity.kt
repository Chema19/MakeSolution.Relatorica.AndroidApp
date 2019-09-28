package makesolution.relatorica.activities

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        startButton.setOnClickListener {
            var url: String = RelatoricaApi.loginUrlPost
            var usernameET = userEditText.text.toString()
            var passwordET = passwordEditText.text.toString()

            RelatoricaApi.PostLogin(url, usernameET, passwordET,
                { response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }
    private fun handleResponse(response: LoginResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        var preferences : SharedPreferences = this.getSharedPreferences(getString(R.string.string_preference),MODE_PRIVATE)

        val sp = preferences.edit()
        sp.putString(getString(R.string.token), response.Data!!.Token)
        sp.putInt(getString(R.string.personid), response.Data!!.PersonId)
        sp.apply()

        if (preferences.getBoolean(getString(R.string.string_preference), false)) {
            val localIntent = Intent(this, MainActivity::class.java)
            startActivity(localIntent)
            finish()
        }

        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
