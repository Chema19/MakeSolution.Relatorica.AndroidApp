package makesolution.relatorica.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

import makesolution.relatorica.R
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.FatherProfileResponse

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)


        val result = this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var usuarioId = result.getInt(getString(R.string.personid), 0)

        var url: String = RelatoricaApi.getFatherProfile(usuarioId)

        RelatoricaApi.GetFatherById(token,url,
            { response -> handleResponse(response, view) },
            { error -> handleError(error) })

        return view
    }

    private fun handleResponse(response: FatherProfileResponse?, view: View){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        view.nombreViewEditText.text  = response.Data!!.Nombres
        view.apellidoViewEditText.text = response.Data!!.Apellidos
        view.credencialesViewEditText.text = response.Data!!.Credenciales
        view.correoViewEditText.text = response.Data!!.Correo
        view.celularViewEditText.text = response.Data!!.Celular
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }

}
