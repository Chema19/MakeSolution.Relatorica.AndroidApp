package makesolution.relatorica.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import makesolution.relatorica.R

import kotlinx.android.synthetic.main.activity_home_tutorial.*

class HomeTutorialActivity : AppCompatActivity() {

    val APIKEY ="AIzaSyDGgAf4Mq6KFcQwdYAzJn8wfWg3D-b6YAY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tutorial)
        btn_load.setOnClickListener({
            val intent = YouTubeStandalonePlayer.createVideoIntent(this,APIKEY,"SsBjJ3o7K04")
            startActivity(intent)
        })



    }

}
