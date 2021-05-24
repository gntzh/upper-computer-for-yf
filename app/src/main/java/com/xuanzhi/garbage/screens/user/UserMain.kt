package com.xuanzhi.garbage.screens.user

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wuba.wastesorting.components.LocaleContextWrapper
import com.xuanzhi.garbage.MainViewModel
import com.xuanzhi.garbage.R
import com.xuanzhi.garbage.components.TheBackground

@Composable
fun UserMain(vm: MainViewModel = viewModel(), onToUserEnd: ()->Unit = {}){
    TheBackground {
        LocaleContextWrapper {
            Image(
                painter = painterResource(R.drawable.user_main1),
                contentDescription = null,
                modifier = Modifier.padding(top = 36.dp, bottom = 36.dp,  start = 30.dp)
                    .clickable { onToUserEnd() }// TODO remove
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GarbageBin(R.drawable.kitchen_waste, percentage = 10)
            GarbageBin(R.drawable.recyclable_waste, percentage = 20)
            GarbageBin(R.drawable.harmful_waste, percentage = 100)
        }
    }
}

@Composable
fun GarbageBin(@DrawableRes imageId: Int, percentage: Int){
    Box(contentAlignment = Alignment.Center){
        Image(painter = painterResource(imageId), contentDescription = null)
        Text(
            "$percentage%",
            color = if (percentage >= 100) Color.Red else Color.White,
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 240.dp)
        )
    }
}

@Preview
@Composable
fun GarbageBinPreview(){
    GarbageBin(R.drawable.recyclable_waste, 100)
}
