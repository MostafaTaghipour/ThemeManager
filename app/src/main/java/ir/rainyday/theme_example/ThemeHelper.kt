package ir.rainyday.theme_example

import android.app.Fragment
import android.content.Context
import android.view.ContextThemeWrapper
import ir.rainyday.thememanager.ThemeManager
import androidx.fragment.app.Fragment as SupportFragment


val Context.accentColor: Int
    get() = ThemeManager.getInstance().getColorAccent(this)

val Context.primaryColor: Int
    get() = ThemeManager.getInstance().getColorPrimary(this)

val Context.primaryColorDark: Int
    get() = ThemeManager.getInstance().getColorPrimaryDark(this)

fun ContextThemeWrapper.applyTheme() =  ThemeManager.getInstance().applyTheme(this)


val Fragment.accentColor: Int
    get() = activity.accentColor

val Fragment.primaryColor: Int
    get() = activity.primaryColor

val Fragment.primaryColorDark: Int
    get() = activity.primaryColorDark

fun Fragment.applyTheme() =  activity.applyTheme()


val SupportFragment.accentColor: Int?
    get() = context?.accentColor

val SupportFragment.primaryColor: Int?
    get() = context?.primaryColor

val SupportFragment.primaryColorDark: Int?
    get() = context?.primaryColorDark

fun SupportFragment.applyTheme() =  activity?.applyTheme()

