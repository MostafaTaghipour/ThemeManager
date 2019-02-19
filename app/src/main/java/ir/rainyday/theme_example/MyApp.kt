package ir.rainyday.theme_example

import android.app.Application
import ir.rainyday.thememanager.ThemeManager

/**
 * Created by mostafa-taghipour on 10/31/17.
 */

class  MyApp:Application(){


    override fun onCreate() {
        super.onCreate()

        ThemeManager.getInstance().currentTheme = ThemePrefrences.getInstance(this).theme ?: R.style.AppTheme_NoActionBar_Red
        ThemeManager.getInstance().nightMode = ThemePrefrences.getInstance(this).nightMode
    }
}