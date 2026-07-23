package com.app.kmp_app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.app.kmp_app.ui.screens.SplashScreen
import com.app.kmp_app.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            BottomSheetNavigator {
                Navigator(SplashScreen()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}