package makesolution.relatorica.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.card_paragraph.view.*
import makesolution.relatorica.R
import makesolution.relatorica.models.ParagraphModel
import makesolution.relatorica.networks.RelatoricaApi

class ParagraphAdapter (var paragraphs :ArrayList<ParagraphModel>, val context: Context): RecyclerView.Adapter<ParagraphAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.card_paragraph, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return paragraphs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val purchase = paragraphs.get(position)
        holder.updateFrom(context, purchase)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textoTextView = view.textoTextView
        val ordenTextView = view.nameTextView
        var paragraphCardView = view.paragraphCardView

        fun updateFrom(context: Context, paragraph: ParagraphModel){

            textoTextView.text = paragraph.Texto
            ordenTextView.text = "Parrafo N° " + paragraph.Orden.toString()

            //val result =  context.getSharedPreferences(context.getString(R.string.string_preference), Context.MODE_PRIVATE) //this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
            //var token = "Bearer " + result.getString(context.getString(R.string.token), "")
            //var url: String = RelatoricaApi.getHistory(purchase.HistoriaId)

            //RelatoricaApi.GetHistoriaById(token, url,
            //    { response -> handleResponse(response) },
            //    { error -> handleError(error)})
        }

        //private fun handleResponse(response: HistoryResponse?){
         //   if(true.equals(response!!.Error)){
         //       Log.d("Respuesta Falsa", response!!.Message)
         //       return
         //   }
         //   imagenANImageView.setDefaultImageResId(R.mipmap.ic_launcher)
         //   imagenANImageView.setErrorImageResId(R.mipmap.ic_launcher)
         //   imagenANImageView.setImageUrl(response.Data!!.Imagen)
         //   nombreTextView.text = response.Data!!.Nombre
         //   purchaseCardView.setOnClickListener { view ->
         //       val context = view.context
         //       context.startActivity(
         //           Intent(context, HistoryActivity::class.java)
         //               .putExtras(response.Data.toBundle()))
        //    }
        //}

        //private fun handleError(anError: ANError?){
        //    Log.d("Respuesta Falsa", anError!!.message)
        //}
    }
}