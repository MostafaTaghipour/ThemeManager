package ir.rainyday.theme_example.activities

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import android.view.MenuItem
import android.view.View
import android.widget.*
import ir.rainyday.theme_example.R
import ir.rainyday.theme_example.ThemePrefrences
import ir.rainyday.thememanager.ThemeManager


class SettingActivity : BaseActivity() {

    private val themeManager: ThemeManager by lazy {
        return@lazy ThemeManager.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val spinner = findViewById<Spinner>(R.id.nightModeSpinner)
        themeManager.nightMode?.let {
            spinner. setSelection( if (it == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) 0 else if (it== AppCompatDelegate.MODE_NIGHT_NO) 1 else if (it== AppCompatDelegate.MODE_NIGHT_YES) 2 else 3)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val newValue = if (position == 0) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM else if (position == 1) AppCompatDelegate.MODE_NIGHT_NO else if (position == 2) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_AUTO

                if (themeManager.nightMode == newValue)
                    return

                ThemePrefrences.getInstance(this@SettingActivity).nightMode = newValue
                themeManager.nightMode = newValue
                restart()
            }
        }


        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.check(if (themeManager.currentTheme == R.style.AppTheme_NoActionBar_Red) R.id.radioRed else R.id.radioBlue)
        radioGroup.setOnCheckedChangeListener { radio, id ->
            val newValue = if (id == R.id.radioRed) R.style.AppTheme_NoActionBar_Red else R.style.AppTheme_NoActionBar_Blue

            if (themeManager.currentTheme == newValue)
                return@setOnCheckedChangeListener

            ThemePrefrences.getInstance(this).theme = newValue
            themeManager.currentTheme = newValue
            restart()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // app icon in action bar clicked; go home
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun restart() {
        val launcherIntent = packageManager.getLaunchIntentForPackage(packageName)
        launcherIntent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val settingIntent = Intent(this, SettingActivity::class.java)

        val stack = TaskStackBuilder.create(this)
                .addNextIntent(launcherIntent)
                .addNextIntentWithParentStack(settingIntent)


        finish()
        overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in)

        stack.startActivities()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
