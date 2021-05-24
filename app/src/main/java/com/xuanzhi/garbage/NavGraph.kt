package com.xuanzhi.garbage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xuanzhi.garbage.screens.Login
import com.xuanzhi.garbage.screens.admin.Maintenance
import com.xuanzhi.garbage.screens.user.UserEnd
import com.xuanzhi.garbage.screens.user.UserMain


sealed class RouteRecord(val path: String) {
    object Login : RouteRecord("login")
    object UserMain : RouteRecord("user_main")
    object UserEnd : RouteRecord("user_end")
    object Maintenance : RouteRecord("maintenance")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteRecord.Login.path,
    vm: MainViewModel = hiltViewModel()
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(RouteRecord.Login.path) {
            Login(
                vm,
                onLogIn = {
                    when(it){
                        0 -> navController.navigate(RouteRecord.Maintenance.path){
                            popUpTo(RouteRecord.Maintenance.path)
                        }
                        1 -> navController.navigate(RouteRecord.UserMain.path){
                            popUpTo(RouteRecord.UserMain.path)
                        }
                    }
                }
            )
        }
        composable(RouteRecord.UserMain.path) { UserMain(vm, onToUserEnd = {navController.navigate(RouteRecord.UserEnd.path)}) }
        composable(RouteRecord.UserEnd.path) {
            UserEnd(
                onEnd = {
                    navController.navigate(RouteRecord.Login.path){
                            popUpTo(RouteRecord.Login.path)
                        }
                }
            )
        }
        composable(RouteRecord.Maintenance.path) { Maintenance(vm) }
    }
}