package ir.rainyday.theme_example

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import ir.rainyday.theme_example.R


class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {

            startActivity(Intent(this, SettingActivity::class.java))

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if (MyApp.instance.ThemeChanged){
            MyApp.instance.ThemeChanged=false
            recreate()
        }
    }



}
