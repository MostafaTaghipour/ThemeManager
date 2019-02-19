# AndroidThemeManager

[![](https://jitpack.io/v/MostafaTaghipour/thememanager.svg)](https://jitpack.io/#MostafaTaghipour/thememanager)

## [iOS version is here](https://github.com/MostafaTaghipour/mtpThemeManager)

AndroidThemeManger is a theme manager for Android:
- Apply theme at runtime
- Support multiple theme
- Supports night mode

![themes](https://raw.githubusercontent.com/MostafaTaghipour/ThemeManager/master/screenshot.gif)

# Requirements

- Api 14+

## Installation

Add JitPack to repositories in your project's root `build.gradle` file:

```Gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency to your module's `build.gradle` file:

```Gradle
dependencies {
    ...
    implementation 'com.github.MostafaTaghipour:thememanager:1.0.0'
}
```


## Usage

- Define app theme (s):

```xml
<resources xmlns:tools="http://schemas.android.com/tools">

  <!-- Base application theme. -->
  <style name="AppTheme" parent="Theme.AppCompat.DayNight">
  </style>

  <style name="AppTheme.NoActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
  </style>

  <style name="AppTheme.NoActionBar.Red">
    <item name="colorPrimary">@color/colorPrimary_Red</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark_Red</item>
    <item name="colorAccent">@color/colorAccent_Red</item>
  </style>

</resources>
```


- Set app theme like below:

```kotlin
ThemeManager.getInstance().currentTheme = R.style.AppTheme_NoActionBar_Red
ThemeManager.getInstance().nightMode = AppCompatDelegate.MODE_NIGHT_YES
```

- Set activities theme like this:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  ThemeManager.getInstance().applyTheme(this)
}
```

- Thats it, enjoy it



## Author

Mostafa Taghipour, mostafa@taghipour.me

## License

AndroidThemeManager is available under the MIT license. See the LICENSE file for more info.
