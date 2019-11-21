package makesolution.relatorica.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.card_purchase.view.*
import makesolution.relatorica.R
import makesolution.relatorica.activities.ParagraphActivity
import makesolution.relatorica.models.ChildModel

import makesolution.relatorica.models.StoreModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.HistoryResponse


/**



class   ChildAdapter(var childes:ArrayList<ChildModel>, val context: Context): RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_child, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return childes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childs = childes.get(position)
        holder.updateFrom(context, childs)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imagenANImageView = view.imageANImageView
        val nombreTextView = view.nameBookTextView
        var purchaseCardView = view.purchaseCardView

        fun updateFrom(context: Context, purchase: ChildModel){

            val result =  context.getSharedPreferences(context.getString(R.string.string_preference), Context.MODE_PRIVATE) //this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
            var token = "Bearer " + result.getString(context.getString(R.string.token), "")
            var url: String = RelatoricaApi.getChildByFather()

            RelatoricaApi.GetHistoriaById(token, url,
                { response -> handleResponse(response) },
                { error -> handleError(error)})
        }

        private fun handleResponse(response: HistoryResponse?){
            if(true.equals(response!!.Error)){
                Log.d("Respuesta Falsa", response!!.Message)
                return
            }

           txt_child.text = response.Data!!.Nombre
            purchaseCardView.setOnClickListener { view ->
                val context = view.context
                var history = StoreModel(response.Data.HistoriaId,response.Data.Nombre,response.Data.UsuarioId,response.Data.NombreUsuario,response.Data.Argumento,response.Data.FechaRegistro,response.Data.Estado,response.Data.Precio,response.Data.Editorial,response.Data.Imagen)

                context.startActivity(
                    Intent(context, ParagraphActivity::class.java)
                        .putExtras(history.toBundle()))
            }
        }

        private fun handleError(anError: ANError?){
            Log.d("Respuesta Falsa", anError!!.message)
        }
    }
}
 */