package makesolution.relatorica.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_edit_child.*
import makesolution.relatorica.R
import makesolution.relatorica.models.ChildModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.ChildResponse
import java.text.SimpleDateFormat
import java.util.*

class EditChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_child)

        editChildTextView.setOnClickListener {
            val result = this!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
            var token = "Bearer " + result.getString(getString(R.string.token), "")
            var childId=intent.extras.getBundle("HijoId").toString().toInt()
            var padreId =  result.getInt(getString(R.string.personid),0)
            var url:String=RelatoricaApi.childUrlPost
            var childNameET:String=nombreCompletoEditText.text.toString()
            var birthDateET:String=fechaNacimientoEditText.text.toString()
            var c=Calendar.getInstance().time
            var df=SimpleDateFormat("yyyy-MMM-dd")
            var formattedDate=df.format(c)
            var child=ChildModel(NombreCompleto = childNameET,FechaNacimiento = birthDateET,Estado ="",FechaRegistro = formattedDate,HijoId = childId,PadreId = padreId)
            RelatoricaApi.PostChild(url,child,token,{ response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }
    private fun handleResponse(response: ChildResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
