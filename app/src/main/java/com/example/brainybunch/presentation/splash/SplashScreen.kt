package com.example.brainybunch.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SplashViewModel>()
    val isLogin by viewModel.isLogin.collectAsState()

    LaunchedEffect(Unit) {
        delay(4000)
        if (isLogin){
            navController.navigate("home")
        } else{
            navController.navigate("login")
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF3D3D3D)), contentAlignment = Alignment.Center) {
        AsyncImage(model = R.drawable.logo_only, contentDescription = "Logo Icon")
    }
}