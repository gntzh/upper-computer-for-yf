package com.xuanzhi.garbage.utils


import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.xuanzhi.garbage.ui.theme.GarbageTheme


@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    GarbageTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}