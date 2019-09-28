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
import kotlinx.android.synthetic.main.fragment_store.view.*

import makesolution.relatorica.R
import makesolution.relatorica.adapters.StoreAdapter
import makesolution.relatorica.models.StoreModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.StoreResponse

/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : Fragment() {

    lateinit var storeRecyclerView: RecyclerView
    lateinit var stores: ArrayList<StoreModel>
    lateinit var storeAdapter: StoreAdapter
    lateinit var storeLayoutManager: RecyclerView.LayoutManager

    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_store,container,false)

        storeRecyclerView = view.booksStoreRecyclerView
        stores = ArrayList()
        storeAdapter = StoreAdapter(stores, view.context)
        storeLayoutManager = GridLayoutManager(view.context, 1)
        storeRecyclerView.adapter = storeAdapter
        storeRecyclerView.layoutManager = storeLayoutManager

        //val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        //var token = "Bearer " + result.getString(TOKEN, "")

        var url: String = RelatoricaApi.historiaUrlGet

        RelatoricaApi.GetHistoriasNormalOrById("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IkFkbWluIiwibmJmIjoxNTY5NjUyNjQ4LCJleHAiOjE1Njk2NTk4NDgsImlhdCI6MTU2OTY1MjY0OCwiaXNzIjoiaHR0cDovLzE4LjE4OC4xMDIuMjMwL1JlbGF0b3JpY2EvIiwiYXVkIjoiaHR0cDovLzE4LjE4OC4xMDIuMjMwL1JlbGF0b3JpY2EvIn0.EPDCrTxOOITGuLxPYbja7_uGBw66acrbQb92qHe1qdc", url,
            { response -> handleResponse(response) },
            { error -> handleError(error)})

        return view

    }

    private fun handleResponse(response: StoreResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }

        stores = response.Data!!
        storeAdapter.stores = stores
        storeAdapter.notifyDataSetChanged()

    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }

}
