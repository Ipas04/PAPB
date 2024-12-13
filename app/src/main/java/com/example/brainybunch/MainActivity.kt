package com.example.brainybunch

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.brainybunch.component.BottomNavBar
import com.example.brainybunch.presentation.article.ArticleScreen
import com.example.brainybunch.presentation.community.CommunityScreen
import com.example.brainybunch.presentation.home.HomeScreen
import com.example.brainybunch.presentation.login.LoginScreen
import com.example.brainybunch.presentation.register.RegisterScreen
import com.example.brainybunch.presentation.scan.ScanScreen
import com.example.brainybunch.presentation.splash.SplashScreen
import com.example.brainybunch.presentation.track_bin.TrackBinScreen
import com.example.brainybunch.ui.theme.BrainyBunchTheme
import dagger.hilt.android.AndroidEntryPoint

lateinit var navController: NavHostController
lateinit var mainViewModel: MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        val _mainViewModel by viewModels<MainViewModel>()
        mainViewModel = _mainViewModel
        setContent {

            navController = rememberNavController()

            //BOTTOM NAVBAR CHECKER
            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination.route?.let {
                    mainViewModel.currentRoute.value = it

                    when (it) {
                        "home", "scan", "track", "article", "community", "track_bin" -> mainViewModel.showBottomBar.value =
                            true

                        else -> mainViewModel.showBottomBar.value = false
                    }
                }
            }

            BrainyBunchTheme(darkTheme = false) {
                Box(modifier = Modifier.fillMaxSize()){

                    Scaffold(containerColor = Color.Transparent,
                        bottomBar = {
                            if(mainViewModel.showBottomBar.value){
                                BottomNavBar()
                            }
                        }
                    ) {

                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = "splash"
                        ) {
                            composable("splash") {
                                SplashScreen(navController = navController)
                            }

                            composable("home") {
                                HomeScreen(navController = navController)
                            }
                            composable("login") {
                                LoginScreen(navController = navController)
                            }

                            composable("register") {
                                RegisterScreen(navController = navController)
                            }
                            composable("scan") {
                                ScanScreen(navController = navController)
                            }
                            composable("article") {
                                ArticleScreen(navController = navController)
                            }
                            composable("community") {
                                CommunityScreen(navController = navController)
                            }

                            composable("track_bin") {
                                TrackBinScreen(navController = navController)
                            }
                        }
                    }

                    if(mainViewModel.showBottomBar.value){
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 72.dp), contentAlignment = Alignment.BottomCenter){
                            AsyncImage(modifier =Modifier.size(86.dp).clickable {
                                navController.navigate("track_bin")
                            }, model = R.drawable.icon_track_bin, contentDescription = "")
                        }
                    }
                }


            }
        }
    }
}
