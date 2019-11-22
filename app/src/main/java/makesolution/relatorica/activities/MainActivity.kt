package makesolution.relatorica.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import makesolution.relatorica.R
import makesolution.relatorica.fragments.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_store

    }
    private fun fragmentFor(item: MenuItem) : Fragment {
        when(item.itemId){
            /*
            R.id.navigation_home -> {
                return HomeFragment()
            }*/
            R.id.navigation_childs -> {
                return ChildFragment()
            }
            R.id.navigation_my_books -> {
                return BookFragment()
            }
            R.id.navigation_store -> {
                return StoreFragment()
            }
            /*
            R.id.navigation_profile -> {
                return ProfileFragment()
            }*/
        }
        return HomeFragment()
    }
    private fun navigateTo(item: MenuItem): Boolean{
        item.setChecked(true)
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmentFor(item))
            .commit() > 0

    }
    fun onYoutube(view: View){
        val intent = Intent(this, HomeTutorialActivity::class.java)
        startActivity(intent)

    }

}
