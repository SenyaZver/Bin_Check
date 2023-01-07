package com.example.bin_check.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bin_check.presentation.Routes.historyScreenRoute
import com.example.bin_check.presentation.Routes.mainScreenRoute
import com.example.bin_check.presentation.history_screen.HistoryScreen
import com.example.bin_check.presentation.main_screen.MainScreen
import com.example.bin_check.presentation.theme.Binlist_CheckTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Binlist_CheckTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = mainScreenRoute
                    ) {
                        composable(mainScreenRoute) {
                            MainScreen(navController)
                        }
                        composable(historyScreenRoute) {
                            HistoryScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

object Routes {
    val mainScreenRoute = "MainScreenRoute"
    val historyScreenRoute = "HistoryScreenRoute"
}

