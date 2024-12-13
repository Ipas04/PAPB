package com.example.brainybunch.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.mainViewModel
import com.example.brainybunch.navController

@Composable
fun BottomNavBar() {
    BottomAppBar(
        modifier = Modifier.height(100.dp),
        containerColor = Color.Transparent,
        //backgroundColor = Color.Transparent
        //tonalElevation = 0.dp
    ) {
        Row {
            //ITEM 1
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(64.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(
                                if (mainViewModel.currentRoute.value == "home") Color(
                                    0xFF3F4E3E
                                ) else Color.White
                            )
                            .clickable {
                                navController.navigate("home")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_home),
                            contentDescription = "Icon Beranda Bottom Bar",
                            tint = if (mainViewModel.currentRoute.value == "home") Color.White else Color(0xFF3F4E3E)
                        )
                        Text(
                            text = "Home",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (mainViewModel.currentRoute.value == "home") Color.White else Color(0xFF3F4E3E)
                        )
                    }
                }
            }

            //ITEM 2
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(64.dp)
            ) {
                Column {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(
                                if (mainViewModel.currentRoute.value == "scan") Color(
                                    0xFF3F4E3E
                                ) else Color.White
                            )
                            .clickable {
                                navController.navigate("scan")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_scan),
                            contentDescription = "Icon Beranda Bottom Bar",
                            tint = if (mainViewModel.currentRoute.value == "scan") Color.White else Color(0xFF3F4E3E)
                        )
                        Text(
                            text = "Scan",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (mainViewModel.currentRoute.value == "scan") Color.White else Color(0xFF3F4E3E)
                        )
                    }
                }
            }

            //ITEM 3
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            if (mainViewModel.currentRoute.value == "track_bin") Color(
                                0xFF3F4E3E
                            ) else Color.White
                        )
                )
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate("track_bin")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
//                        AsyncImage(
//                            modifier = Modifier.size(75.dp),
//                            model = R.drawable.icon_track_bin,
//                            contentDescription = "Icon Beranda Bottom Bar",
//                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = "Track Bin",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (mainViewModel.currentRoute.value == "track_bin") Color.White else Color(0xFF3F4E3E)
                        )
                    }
                }
            }

            //ITEM 4
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(64.dp)
            ) {
                Column {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(
                                if (mainViewModel.currentRoute.value == "article") Color(
                                    0xFF3F4E3E
                                ) else Color.White
                            )
                            .clickable {
                                navController.navigate("article")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_article),
                            contentDescription = "Icon Beranda Bottom Bar",
                            tint = if (mainViewModel.currentRoute.value == "article") Color.White else Color(0xFF3F4E3E)
                        )
                        Text(
                            text = "Article",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (mainViewModel.currentRoute.value == "article") Color.White else Color(0xFF3F4E3E)
                        )
                    }
                }
            }
            //ITEM 5
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(64.dp)
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(
                                if (mainViewModel.currentRoute.value == "community") Color(
                                    0xFF3F4E3E
                                ) else Color.White
                            )
                            .clickable {
                                navController.navigate("community")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.icon_community),
                            contentDescription = "Icon Beranda Bottom Bar",
                            tint = if (mainViewModel.currentRoute.value == "community") Color.White else Color(0xFF3F4E3E)
                        )
                        Text(
                            text = "Community",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (mainViewModel.currentRoute.value == "community") Color.White else Color(0xFF3F4E3E)
                        )
                    }
                }
            }
        }
    }
}