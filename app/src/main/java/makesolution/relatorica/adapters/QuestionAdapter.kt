package makesolution.relatorica.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_question.view.*

import makesolution.relatorica.R
import makesolution.relatorica.models.QuestionModel
import makesolution.relatorica.networks.RelatoricaApi

class QuestionAdapter (var questions:ArrayList<QuestionModel>, val context: Context): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.card_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions.get(position)
        holder.updateFrom(context, question)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val enunciadoTextView = view.enunciadoTextView

        fun updateFrom(context: Context, question: QuestionModel){
            enunciadoTextView.text=question.Enunciado
        }
    }
}