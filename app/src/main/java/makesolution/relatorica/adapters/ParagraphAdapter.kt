package makesolution.relatorica.adapters

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.card_paragraph.*
import kotlinx.android.synthetic.main.card_paragraph.view.*
import makesolution.relatorica.R
import makesolution.relatorica.models.ParagraphModel
import makesolution.relatorica.models.SoundModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.SoundResponse



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
        lateinit var sound: SoundModel
        lateinit var mediaPlayer: MediaPlayer
        val textoTextView = view.textoTextView
        val ordenTextView = view.nameTextView
        val playImageView = view.playImageView
        val stopImageView = view.stopImageView
        fun updateFrom(context: Context, paragraph: ParagraphModel){
            textoTextView.text = paragraph.Texto
            ordenTextView.text = "Parrafo N° " + paragraph.Orden.toString()
            playImageView.bringToFront()
            stopImageView.bringToFront()
            val result =  context.getSharedPreferences(context.getString(R.string.string_preference), Context.MODE_PRIVATE) //this.activity!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
            var token = "Bearer " + result.getString(context.getString(R.string.token), "")
            var url: String = RelatoricaApi.getSonidoById(paragraph.SonidoId)

            playImageView.setOnClickListener{
                playSound()
            }
            stopImageView.setOnClickListener{
                stopSound()
            }
            RelatoricaApi.GetSoundById(token, url,
                { response -> handleResponse(response) },
                { error -> handleError(error)})
        }
        private fun handleResponse(response: SoundResponse?){

            if(true.equals(response!!.Error)){
               Log.d("Respuesta Falsa", response!!.Message)
               return
           }
            sound = response.Data!!
        }

        private fun handleError(anError: ANError?){
            Log.d("Respuesta Falsa", anError!!.message)
        }

        fun playSound(){
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(sound.Url)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
        fun stopSound(){
            mediaPlayer.stop()
        }
    }
}