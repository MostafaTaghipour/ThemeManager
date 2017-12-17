
# Android theme example
​
​In this tutorial, I'm going to show you how to use multi theme in your project, Also a tutorial on how to use DayNight theme.
​
​![typography](/screenshot.gif)
​
​
​- Define appTheme like below:
​
​```xml
​<resources xmlns:tools="http://schemas.android.com/tools">
​
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.DayNight">
    </style>
    
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
    
    
    <style name="AppTheme.NoActionBar.Blue">
        <item name="colorPrimary">@color/colorPrimary_Blue</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark_Blue</item>
        <item name="colorAccent">@color/colorAccent_Blue</item>
    </style>
    
    
    <style name="AppTheme.NoActionBar.Red">
        <item name="colorPrimary">@color/colorPrimary_Red</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark_Red</item>
        <item name="colorAccent">@color/colorAccent_Red</item>
    </style>
    
    
    <style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar"/>
    
    <style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />
​
​</resources>
​
​```
​
​
​- Set application and activities theme in AndroidManifest.xml
​
​```xml
​
​<application
    ...
    android:theme="@style/AppTheme">
    
    <activity
        android:name="ir.rainyday.theme_example.MainActivity"
        android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
    </activity>
    <activity
        android:name="ir.rainyday.theme_example.SettingActivity"
        android:theme="@style/AppTheme.NoActionBar">
    </activity>
​</application>
​
​```
​
​
​- Define ThemeManager class like below:
​
​```kotlin
​class ThemeManager private constructor(var context: Context) {
​
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
​}
​```
​
​- Set activities theme like this:
​
​```kotlin
​override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    updateTheme()
​}
​
​fun updateTheme() {
    val themeManager = ThemeManager.getInstance(this)
    setTheme(themeManager.theme)
    AppCompatDelegate.setDefaultNightMode(themeManager.nightMode)
​}
​```
​
​
​- Thats it, enjoy it
​

