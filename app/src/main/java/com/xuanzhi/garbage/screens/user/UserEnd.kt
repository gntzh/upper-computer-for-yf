package com.xuanzhi.garbage.screens.user

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuba.wastesorting.components.LocalLocaleContext
import com.wuba.wastesorting.components.LocaleContextWrapper
import com.xuanzhi.garbage.R

@Composable
fun UserEnd(
    onEnd: () -> Unit = {}
) {
    val context = LocalLocaleContext.current
    var mediaPlayer: MediaPlayer?
    DisposableEffect(context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.allowed_to_serve)
        mediaPlayer?.setOnCompletionListener {
            onEnd()
        }
        mediaPlayer?.start()
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
    LocaleContextWrapper() {
        Image(
            painter = painterResource(R.drawable.user_end_bg),
            contentDescription = null
        )
    }
}
