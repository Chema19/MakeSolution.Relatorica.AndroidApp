package makesolution.relatorica.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_book.view.*

import makesolution.relatorica.R
import makesolution.relatorica.adapters.PurchaseAdapter
import makesolution.relatorica.models.PurchaseModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.PurchaseResponse

/**
 * A simple [Fragment] subclass.
 */
class BookFragment : Fragment() {

    lateinit var purchaseRecyclerView: RecyclerView
    lateinit var purchases: ArrayList<PurchaseModel>
    lateinit var purchaseAdapter: PurchaseAdapter
    lateinit var purchaseLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book,container,false)

        purchaseRecyclerView = view.booksRecyclerView
        purchases = ArrayList()
        purchaseAdapter = PurchaseAdapter(purchases, view.context)
        purchaseLayoutManager = GridLayoutManager(view.context, 1)
        purchaseRecyclerView.adapter = purchaseAdapter
        purchaseRecyclerView.layoutManager = purchaseLayoutManager


        val result = this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var padreId =  result.getInt(getString(R.string.personid),0)
        var url: String = RelatoricaApi.getPurchasesByFather(padreId)

        RelatoricaApi.GetCompra(token, url,
            { response -> handleResponse(response) },
            { error -> handleError(error)})

        return view
    }

    private fun handleResponse(response: PurchaseResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }

        purchases = response.Data!!
        purchaseAdapter.purchases = purchases
        purchaseAdapter.notifyDataSetChanged()

    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }

}
