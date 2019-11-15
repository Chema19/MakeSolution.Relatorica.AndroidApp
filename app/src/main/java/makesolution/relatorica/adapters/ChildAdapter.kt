package makesolution.relatorica.adapters
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.card_child.view.*
import makesolution.relatorica.R
import makesolution.relatorica.activities.EditChildActivity
import makesolution.relatorica.activities.MainActivity
import makesolution.relatorica.models.ChildModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.ChildProfileResponse

class ChildAdapter(var childs:ArrayList<ChildModel>, val context: Context): RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.card_child, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return childs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val child = childs.get(position)
        holder.updateFrom(context, child)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val childNameTextView = view.childNameTextView
        val birthDateTextVIew=view.birthDateTextView
        val childCardView=view.childCardView
        fun updateFrom(context: Context, child: ChildModel){

            val result =  context.getSharedPreferences(context.getString(R.string.string_preference), Context.MODE_PRIVATE) //this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
            var token = "Bearer " + result.getString(context.getString(R.string.token), "")
            var url: String = RelatoricaApi.getChildById(child.HijoId)
            childNameTextView.text = child.NombreCompleto

            birthDateTextVIew.text=child.FechaNacimiento
            RelatoricaApi.GetChildById(token, url,
                { response -> handleResponse(response) },
                { error -> handleError(error)})
        }

        private fun handleResponse(response: ChildProfileResponse?){
            if(true.equals(response!!.Error)){
                Log.d("Respuesta Falsa", response!!.Message)
                return
            }
            childNameTextView.text = response.Data!!.NombreCompleto
            birthDateTextVIew.text=response.Data!!.FechaNacimiento
            childCardView.setOnClickListener { view ->
                val context = view.context
                var child = ChildModel(response.Data.HijoId,response.Data.NombreCompleto,response.Data.Estado,response.Data.FechaRegistro,response.Data.FechaNacimiento,response.Data.PadreId)

                context.startActivity(
                    Intent(context, EditChildActivity::class.java)
                        .putExtras(child.toBundle()))
            }

        }

        private fun handleError(anError: ANError?){
            Log.d("Respuesta Falsa", anError!!.message)
        }
    }
}