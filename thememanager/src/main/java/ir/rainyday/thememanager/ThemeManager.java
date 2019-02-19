package ir.rainyday.thememanager;

import android.content.Context;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;


import androidx.annotation.AttrRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {

    //region Singleton
    private static volatile ThemeManager instance;
    private static final Object mutex = new Object();

    public static ThemeManager getInstance() {
        ThemeManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new ThemeManager();
            }
        }
        return result;
    }
    //endregion

    //region Properties
    @Nullable
    private Integer mCurrentTheme = null;

    @Nullable
    public Integer getCurrentTheme() {
        return mCurrentTheme;
    }

    public void setCurrentTheme(@Nullable Integer mCurrentTheme) {
        this.mCurrentTheme = mCurrentTheme;
    }


    @Nullable
    private Integer mNightMode = null;

    @Nullable
    public Integer getNightMode() {
        return mNightMode;
    }

    public void setNightMode(@Nullable Integer mNightMode) {
        this.mNightMode = mNightMode;
    }

    //endregion

    //region Methods
    public TypedValue getTypedValueForAttr(Context context, @AttrRes int attrRes) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue;
    }

    public int getColorPrimary(Context context) {
        return getTypedValueForAttr(context, R.attr.colorPrimary).data;
    }


    public int getColorPrimaryDark(Context context) {
        return getTypedValueForAttr(context, R.attr.colorPrimaryDark).data;
    }

    public int getColorAccent(Context context) {
        return getTypedValueForAttr(context, R.attr.colorAccent).data;
    }

    public void applyTheme(ContextThemeWrapper contextThemeWrapper) {
        if (mCurrentTheme != null)
            contextThemeWrapper.setTheme(mCurrentTheme);

        if (mNightMode != null)
            AppCompatDelegate.setDefaultNightMode(mNightMode);
    }
    //endregion
}
