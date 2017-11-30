package ir.rainyday.theme_example

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.annotation.AttrRes
import android.support.v7.app.AppCompatDelegate
import android.util.TypedValue
import ir.rainyday.theme_example.R


/**
 * Created by mostafa-taghipour on 10/30/17.
 */


class ThemeManager private constructor(var context: Context) {

    private val PREFS_THEME_KEY = "prefs_theme_key"
    private val PREFS_NIGHT_KEY = "prefs_night_key"


    companion object {

        private var instance: ThemeManager? = null

        fun getInstance(context: Context): ThemeManager {
            if (instance == null)  // NOT thread safe!
                instance = ThemeManager(context)

            return instance!!
        }
    }


    private  var prefs:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private  var _theme:Int = 0

    init {
        _theme = prefs.getInt(PREFS_THEME_KEY, 0)
    }


    var theme: Int
        get() = _theme
        set(theme) {
            prefs.edit().putInt(PREFS_THEME_KEY, theme).apply()
            _theme = theme
        }

    private var _nightMode: Int = prefs.getInt(PREFS_NIGHT_KEY, AppCompatDelegate.MODE_NIGHT_NO)
    var nightMode: Int
        get() = _nightMode
        set(value) {
            prefs.edit().putInt(PREFS_NIGHT_KEY, value).apply()
            _nightMode = value
        }


    fun getTypedValueForAttr(@AttrRes attrRes: Int): TypedValue {
        val typedValue = TypedValue()
        context.getTheme().resolveAttribute(attrRes, typedValue, true)
        return typedValue
    }

    val colorPrimary: Int
        get() {
            return getTypedValueForAttr(R.attr.colorPrimary).data
        }


    val colorPrimaryDark: Int
        get() {
            return getTypedValueForAttr(R.attr.colorPrimaryDark).data
        }

    val colorAccent: Int
        get() {
            return getTypedValueForAttr(R.attr.colorAccent).data
        }


}

