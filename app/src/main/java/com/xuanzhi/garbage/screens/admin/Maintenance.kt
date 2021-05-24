package com.xuanzhi.garbage.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wuba.wastesorting.components.LocaleContextWrapper
import com.xuanzhi.garbage.MainViewModel
import com.xuanzhi.garbage.R
import com.xuanzhi.garbage.components.TheBackground


@Composable
fun Maintenance(vm: MainViewModel = viewModel()){
    val color = Color(0xfff9ac19)
    TheBackground {
        Text("维护模式", fontSize = 48.sp, modifier = Modifier.padding(horizontal = 24.dp, vertical = 36.dp))

        Row(Modifier.padding(horizontal = 100.dp)) {
            Card(
                border = BorderStroke(1.dp, Color.Black),
                contentColor = color,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(Modifier.width(300.dp)) {
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("电机", fontSize = 32.sp, color = Color.Black)
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("电机1", fontSize = 24.sp)
                        Button(
                            colors = ButtonDefaults.buttonColors(color, contentColor = Color.White),
                            onClick = {
                                vm.sendCommand("1")
                        }) {
                            Text("ddd")
                        }
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("电机2-1", fontSize = 24.sp)
                        Button(
                            colors = ButtonDefaults.buttonColors(color, contentColor = Color.White),
                            onClick = {
                            }) {
                            Text("ddd")
                        }
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("电机2-2", fontSize = 24.sp)
                        Button(
                            colors = ButtonDefaults.buttonColors(color, contentColor = Color.White),
                            onClick = {
                            }) {
                            Text("ddd")
                        }
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("电机2-3", fontSize = 24.sp)
                        Button(
                            colors = ButtonDefaults.buttonColors(color, contentColor = Color.White),
                            onClick = {
                            }) {
                            Text("ddd")
                        }
                    }
                }
            }
            Spacer(Modifier.width(150.dp))
            Card(
                border = BorderStroke(1.dp, Color.Black),
                contentColor = color,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(Modifier.width(320.dp)) {
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("传感器", fontSize = 32.sp, color = Color.Black)
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth()
                    ) {
                        Text("距离传感器1", fontSize = 24.sp, modifier = Modifier.alignByBaseline())
                        Spacer(Modifier.width(30.dp))
                        Text("122mm", fontSize = 18.sp, color = Color.Black, modifier = Modifier.alignByBaseline())
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth()
                    ) {
                        Text("距离传感器2", fontSize = 24.sp, modifier = Modifier.alignByBaseline())
                        Spacer(Modifier.width(30.dp))
                        Text("122mm", fontSize = 18.sp, color = Color.Black, modifier = Modifier.alignByBaseline())
                    }
                    Divider(color = color)
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .fillMaxWidth()
                    ) {
                        Text("颜色传感器3", fontSize = 24.sp, modifier = Modifier.alignByBaseline())
                        Spacer(Modifier.width(30.dp))
                        Text("112, 11, 110", fontSize = 18.sp, color = Color.Black, modifier = Modifier.alignByBaseline())
                    }
                }
            }
        }
    }
}