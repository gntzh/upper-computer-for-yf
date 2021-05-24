package com.xuanzhi.garbage

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.wuba.wastesorting.components.ResourcesSwitch
import com.xuanzhi.garbage.ui.theme.GarbageTheme
import java.util.*

@Composable
fun App(
    vm: MainViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    ResourcesSwitch(locale = vm.locale) {
        GarbageTheme {
            Surface(color = MaterialTheme.colors.background) {
                NavGraph(navController)
                Row(
                    Modifier.fillMaxSize().padding(end = 12.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "CN", fontSize = 18.sp)
                    Switch(
                        checked = vm.locale === Locale.ENGLISH,
                        onCheckedChange = {
                            vm.switchLocale()
                        },
                        colors = SwitchDefaults.colors(Color(0xfff9ac19)),
                        modifier = Modifier.scale(1.3f).padding(horizontal = 5.dp)
                    )
                    Text(text = "EN", fontSize = 18.sp)
                }
            }
        }
    }
}
