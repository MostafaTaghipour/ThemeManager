package ir.rainyday.theme_example.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.rainyday.thememanager.ThemeManager


/**
 * Created by mostafa-taghipour on 10/31/17.
 */
abstract class BaseActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         ThemeManager.getInstance().applyTheme(this)
    }
}