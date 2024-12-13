package com.example.brainybunch.presentation.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.component.ArticleItem
import com.example.brainybunch.component.FeatureCircle
import com.example.brainybunch.component.TrackingWasteProgress

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val user by viewModel.user.collectAsState()

    val wasteData = listOf(0f, 2f, 5f, 4f, 3f, 6f)


    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp)
                .clip(RoundedCornerShape(bottomEnd = 86.dp))
                .background(Color(0xFF3F4E3E))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                //Notif Icon & Name
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    //notif icon
                    AsyncImage(
                        modifier = Modifier.size(36.dp),
                        model = R.drawable.icon_notification,
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.weight(1f))


                    //name Column
                    Column(horizontalAlignment = Alignment.End) {
                        // Nama
                        Text(
                            text = "Hi, " + user?.name, style = MaterialTheme.typography.bodyMedium,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Right,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Let’s Contribute to our earth",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Right,
                            fontSize = 12.sp,
                            color = Color.White
                        )

                        Button(colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF3F4E3E)
                        ), onClick = {
                            viewModel.logout()
                            navController.navigate("login")
                        }) {
                            Text(text = "Logout", modifier = Modifier.padding(horizontal = 24.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                //tracking progres chart
                TrackingWasteProgress(wasteData)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            //FEATURE APPS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //ITEM FEATURE 1
                FeatureCircle(
                    modifier = Modifier.weight(1f).clickable {
                        navController.navigate("scan")
                    },
                    model = R.drawable.icon_scan_feature,
                    text = "Scan"
                )

                //ITEM FEATURE 2
                FeatureCircle(
                    modifier = Modifier.weight(1f).clickable {
                                                             navController.navigate("track_bin")
                    },
                    model = R.drawable.icon_track_feature,
                    text = "Track"
                )

                //ITEM FEATURE 3
                FeatureCircle(
                    modifier = Modifier.weight(1f).clickable {
                        navController.navigate("article")
                    },
                    model = R.drawable.icon_article_feature,
                    text = "Article"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(24.dp))

            ///ARTICLE SECTIION
            Text(
                text = "Article", style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            //ARTICLE ITEM
            ArticleItem()
            ArticleItem()
            ArticleItem()

        }
    }

}