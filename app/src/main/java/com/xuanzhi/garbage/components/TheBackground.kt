package com.xuanzhi.garbage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wuba.wastesorting.components.LocaleContextWrapper
import com.xuanzhi.garbage.R

@Composable
fun TheBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    LocaleContextWrapper(){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null
        )
    }
    Column(Modifier.padding(top = 150.dp).then(modifier)) {
        content()
    }
}