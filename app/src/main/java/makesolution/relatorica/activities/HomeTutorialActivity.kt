package makesolution.relatorica.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_home_tutorial.*
import makesolution.relatorica.R

class HomeTutorialActivity : AppCompatActivity() {

    val APIKEY ="AIzaSyDGgAf4Mq6KFcQwdYAzJn8wfWg3D-b6YAY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tutorial)

    }
}
