package com.focofitness.appexercicios.presentation.Splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.R
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.DarkBlue
import com.focofitness.appexercicios.ui.theme.MedBlue

@Composable
fun SplashScreen(
    navController: NavHostController ,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val modifier = if (isSystemInDarkTheme()) {
        Modifier.background(Color.Black)
    } else {
        Modifier.background(
            Brush.verticalGradient(listOf(DarkBlue, MedBlue))
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees)
                .size(250.dp),
            painter = painterResource(id = R.mipmap.ic_logo_foreground),
            contentDescription = "logo")

    }

}