package ir.rainyday.theme_example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.content.Intent


/**
 * Created by mostafa-taghipour on 10/31/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    val themeManager by lazy {
        ThemeManager.getInstance(this)
    }


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateTheme()
    }

    fun updateTheme() {
        setTheme(themeManager.theme)
        AppCompatDelegate.setDefaultNightMode(themeManager.nightMode)
    }

    fun recreateActivity(animation: Boolean) {
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        if (animation) {
            overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in)
        } else {
            overridePendingTransition(0, 0)
        }

        startActivity(intent)
        if (animation) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } else {
            overridePendingTransition(0, 0)
        }
    }
}