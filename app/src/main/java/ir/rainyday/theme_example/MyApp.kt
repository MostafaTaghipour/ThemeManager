package ir.rainyday.theme_example

import android.app.Application

/**
 * Created by mostafa-taghipour on 10/31/17.
 */

class  MyApp:Application(){

    companion object{
        lateinit var instance: MyApp
            private set
    }

  var ThemeChanged : Boolean = false

    override fun onCreate() {
        super.onCreate()
        instance =this

        if (ThemeManager.getInstance(this).theme==0)
            ThemeManager.getInstance(this).theme==R.style.AppTheme_NoActionBar_Red
    }
}