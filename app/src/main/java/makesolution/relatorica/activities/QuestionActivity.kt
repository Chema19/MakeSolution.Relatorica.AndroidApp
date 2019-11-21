package makesolution.relatorica.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.androidnetworking.error.ANError
import makesolution.relatorica.R
import makesolution.relatorica.adapters.QuestionAdapter
import makesolution.relatorica.models.QuestionModel
import makesolution.relatorica.networks.RelatoricaApi
import makesolution.relatorica.responses.QuestionResponse

class QuestionActivity : AppCompatActivity() {
    lateinit var questionRecyclerView: RecyclerView
    lateinit var questions: ArrayList<QuestionModel>
    lateinit var questionAdapter: QuestionAdapter
    lateinit var questionLayoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        questions=ArrayList()
        questionRecyclerView=findViewById(R.id.questionsRecyclerView)
        questionRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        questionAdapter=QuestionAdapter(questions = questions, context = this)
        questionLayoutManager=GridLayoutManager(this, 1)
        questionRecyclerView.adapter=questionAdapter
        questionRecyclerView.layoutManager=questionLayoutManager
        val result = this!!.getSharedPreferences(getString(R.string.string_preference),MODE_PRIVATE)
        var token = "Bearer " + result.getString(getString(R.string.token), "")
        var padreId =  result.getInt(getString(R.string.personid),0)
        var url: String = RelatoricaApi.questionUrlGet

        RelatoricaApi.GetQuestions(token, url,
            { response -> handleResponse(response) },
            { error -> handleError(error)})
    }
    private fun handleResponse(response: QuestionResponse?){
        if(true.equals(response!!.Error)){
            Log.d("Respuesta Falsa", response!!.Message)
            return
        }
        questions = response.Data!!
        questionAdapter.questions = questions
        questionAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("Respuesta Falsa", anError!!.message)
    }
}
