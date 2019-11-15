package makesolution.relatorica.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_store.view.*
import makesolution.relatorica.R
import makesolution.relatorica.activities.HistoryActivity
import makesolution.relatorica.models.StoreModel


class StoreAdapter (var stores:ArrayList<StoreModel>, val context: Context): RecyclerView.Adapter<StoreAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.card_store, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = stores.get(position)
        holder.updateFrom(store)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imagenANImageView = view.imageANImageView
        val nombreTextView = view.nameBookTextView
        val precioTextView = view.priceTextView
        var storeCardView = view.storeCardView

        fun updateFrom(store: StoreModel){
            imagenANImageView.setDefaultImageResId(R.mipmap.ic_launcher)
            imagenANImageView.setErrorImageResId(R.mipmap.ic_launcher)
            imagenANImageView.setImageUrl(store.Imagen)
            nombreTextView.text = store.Nombre
            precioTextView.text = "S/. " + store.Precio.toString()
            storeCardView.setOnClickListener { view ->
                val context = view.context
                context.startActivity(Intent(context, HistoryActivity::class.java)
                    .putExtras(store.toBundle()))
            }
        }
    }

}