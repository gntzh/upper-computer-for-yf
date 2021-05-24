package com.wuba.wastesorting.components

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.xuanzhi.garbage.R
import java.util.Locale

val LocalLocaleContext = staticCompositionLocalOf<Context> { error("No locale context found!") }

@Composable
fun ResourcesSwitch(locale: Locale, content: @Composable () -> Unit) {
    val config = LocalContext.current.resources.configuration
    if (Build.VERSION.SDK_INT < 24) {
        config.setLocale(locale)
    } else {
        config.setLocales(LocaleList(locale))
    }
    val localLocaleContext = LocalContext.current.createConfigurationContext(config)
    Log.e("性能埋点", "LocaleSwitch重组")
    CompositionLocalProvider(LocalLocaleContext provides localLocaleContext) {
        content()
    }
}

@Composable
@ReadOnlyComposable
fun localeResources(): Resources {
    return LocalLocaleContext.current.resources
}

@Composable
@ReadOnlyComposable
fun localeStringResource(@StringRes id: Int): String {
    val resources = localeResources()
    return resources.getString(id)
}


@Composable
@ReadOnlyComposable
fun localeStringResource(@StringRes id: Int, vararg formatArgs: Any): String{
    val resources = localeResources()
    return resources.getString(id, *formatArgs)
}

@Composable
fun LocaleContextWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContext provides LocalLocaleContext.current) {
        content()
    }
}
