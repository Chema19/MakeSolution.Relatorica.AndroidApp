package makesolution.relatorica.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError

import makesolution.relatorica.R
import makesolution.relatorica.adapters.PurchaseAdapter
import makesolution.relatorica.models.PurchaseModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.ChildResponse
import makesolution.relatorica.responses.PurchaseResponse

/**
 * A simple [Fragment] subclass.
 */
class ChildFragment : Fragment() {

    lateinit var purchaseRecyclerView: RecyclerView
    lateinit var purchases: ArrayList<PurchaseModel>
    lateinit var purchaseAdapter: PurchaseAdapter
    lateinit var purchaseLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val result = this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var padreId =  result.getInt(getString(R.string.personid),0)
        var url: String = RelatoricaApi.getChildByFather(padreId)

        RelatoricaApi.GetChildByFather(token, url,
            { response -> handleResponse(response) },
            { error -> handleError(error)})

        return view

    }

    private fun handleResponse(response: ChildResponse?){
        if(true.equals(response!!.Error)){

            Log.d("Respuesta Falsa", response!!.Message)

            return
        }
        Log.e("hijo",response.Data.toString())


    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }


}
