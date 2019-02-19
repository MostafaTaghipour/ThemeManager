package ir.rainyday.theme_example

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate


/**
 * Created by mostafa-taghipour on 10/30/17.
 */


class ThemePrefrences private constructor(var context: Context) {

    private val PREFS_THEME_KEY = "prefs_theme_key"
    private val PREFS_NIGHT_KEY = "prefs_night_key"
    private  val  DEFAULT_VALUE = -1

    companion object {

        private var instance: ThemePrefrences? = null

        fun getInstance(context: Context): ThemePrefrences {
            if (instance == null)  // NOT thread safe!
                instance = ThemePrefrences(context)

            return instance!!
        }
    }


    private  var prefs:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private  var _theme:Int
    private var _nightMode: Int

    init {
        _theme = prefs.getInt(PREFS_THEME_KEY, DEFAULT_VALUE)
       _nightMode = prefs.getInt(PREFS_NIGHT_KEY, AppCompatDelegate.MODE_NIGHT_NO)
    }


    var theme: Int?
        get() = if (_theme == DEFAULT_VALUE) null else _theme
        set(theme) {

            val newVal = theme ?: DEFAULT_VALUE
            prefs.edit().putInt(PREFS_THEME_KEY, newVal).apply()
            _theme = newVal
        }


    var nightMode: Int
        get() = _nightMode
        set(value) {
            prefs.edit().putInt(PREFS_NIGHT_KEY, value).apply()
            _nightMode = value
        }
}

