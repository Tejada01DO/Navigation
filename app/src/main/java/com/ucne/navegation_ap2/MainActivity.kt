package com.ucne.navegation_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ucne.navegation_ap2.ui.theme.Navegation_AP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navegation_AP2Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        Button(onClick = {
                            navController.navigate("login")
                        }) {

                        }
                    }
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ){
                        composable("login"){
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)

                            Button(onClick = {
                                navController.navigate("calendar"){
                                    popUpTo("auth"){
                                        inclusive = true
                                    }
                                }
                            }) {
                                Text(text = "Home")
                            }
                        }
                        composable("register"){
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                        }
                        composable("forgot_password"){
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                        }
                    }
                    navigation(
                        startDestination = "calendar_overview",
                        route = "calendar"
                    ){
                        composable("calendar_overview"){

                        }
                        composable("calendar_entry"){

                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}