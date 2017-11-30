package ir.rainyday.theme_example

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.widget.RadioGroup
import android.widget.Switch
import ir.rainyday.theme_example.R


class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);


        val switch = findViewById(R.id.switch1) as Switch
        switch.isChecked = themeManager.nightMode == AppCompatDelegate.MODE_NIGHT_YES
        switch.setOnCheckedChangeListener { _, checked ->
            themeManager.nightMode = if (checked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            recreateActivity(true)
            MyApp.instance.ThemeChanged=true
        }

        val radioGroup = findViewById(R.id.radioGroup) as RadioGroup
        radioGroup.check(if (themeManager.theme == R.style.AppTheme_NoActionBar_Red) R.id.radioRed else R.id.radioBlue)
        radioGroup.setOnCheckedChangeListener { radio, id ->
            if (id == R.id.radioRed) {
                themeManager.theme = R.style.AppTheme_NoActionBar_Red
            } else {
                themeManager.theme = R.style.AppTheme_NoActionBar_Blue
            }

            recreateActivity(true)
            MyApp.instance.ThemeChanged=true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                // app icon in action bar clicked; go home
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
