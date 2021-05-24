package com.xuanzhi.garbage.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wuba.wastesorting.components.LocaleContextWrapper
import com.xuanzhi.garbage.MainViewModel
import com.xuanzhi.garbage.R

@Composable
fun Login(
    vm: MainViewModel = viewModel(),
    onLogIn: (userType: Int) -> Unit = {},
){
    LocaleContextWrapper(){
        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = null,
            modifier = Modifier.clickable {
                onLogIn(1)
            }
        )
    }
}