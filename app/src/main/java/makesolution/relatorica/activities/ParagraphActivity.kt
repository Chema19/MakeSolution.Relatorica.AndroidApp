package makesolution.relatorica.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_paragraph.*
import makesolution.relatorica.R
import makesolution.relatorica.adapters.ParagraphAdapter
import makesolution.relatorica.adapters.PurchaseAdapter
import makesolution.relatorica.models.ParagraphModel
import makesolution.relatorica.models.PurchaseModel
import makesolution.relatorica.models.StoreModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.ParagraphResponse

class ParagraphActivity : AppCompatActivity() {

    lateinit var paragraphRecyclerView: RecyclerView
    lateinit var paragraphs: ArrayList<ParagraphModel>
    lateinit var paragraphAdapter: ParagraphAdapter
    lateinit var paragraphLayoutManager: RecyclerView.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paragraph)

        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        //**********
        paragraphRecyclerView = paragraphViewRecyclerView
        paragraphs = ArrayList()
        paragraphAdapter = ParagraphAdapter(paragraphs, this)
        paragraphLayoutManager = GridLayoutManager(this, 1)
        paragraphRecyclerView.adapter = paragraphAdapter
        //paragraphRecyclerView.layoutManager = paragraphLayoutManager
        paragraphRecyclerView.layoutManager = linearLayoutManager

        val intent = intent ?: return
        val history = StoreModel.from(intent.extras)

        val result = getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var usuarioId = result.getInt(getString(R.string.personid), 0)
        var historiaId = history.HistoriaId

        var url: String = RelatoricaApi.getParagraph(historiaId)

        RelatoricaApi.GetParagraphByHistory(token,url,
               { response -> handleResponse(response) },
               { error -> handleError(error) })
    }

    private fun handleResponse(response: ParagraphResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        paragraphs = response.Data!!
        paragraphAdapter.paragraphs = paragraphs
        paragraphAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
