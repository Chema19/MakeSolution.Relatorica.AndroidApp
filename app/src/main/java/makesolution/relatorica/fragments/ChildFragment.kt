package makesolution.relatorica.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_child.*
import kotlinx.android.synthetic.main.fragment_child.view.*
import makesolution.relatorica.R
import makesolution.relatorica.activities.AddChildActivity
import makesolution.relatorica.activities.EditChildActivity
import makesolution.relatorica.adapters.ChildAdapter
import makesolution.relatorica.models.ChildModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.ChildResponse

/**
 * A simple [Fragment] subclass.
 */
class ChildFragment : Fragment() {
    lateinit var childRecyclerView: RecyclerView
    lateinit var childs: ArrayList<ChildModel>
    lateinit var childAdapter: ChildAdapter
    lateinit var childLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_child,container,false)

        childRecyclerView = view.childsRecyclerView
        childs = ArrayList()
        childAdapter= ChildAdapter(childs, view.context)
        childLayoutManager = GridLayoutManager(view.context, 1)
        childRecyclerView.adapter = childAdapter
        childRecyclerView.layoutManager = childLayoutManager


        val result = this.activity!!.getSharedPreferences(getString(R.string.string_preference), android.support.v7.app.AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var padreId =  result.getInt(getString(R.string.personid),0)
        var url: String = RelatoricaApi.getChildsByFather(padreId)
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
        childs = response.Data!!
        childAdapter.childs = childs
        childAdapter.notifyDataSetChanged()
        addChildFloatingActionButton.setOnClickListener {
            var i=Intent(context, AddChildActivity::class.java)
            startActivity(i)
        }
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
