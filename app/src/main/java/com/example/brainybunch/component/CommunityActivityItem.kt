package com.example.brainybunch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.brainybunch.R

@Composable
fun CommunityActivityItem() {
    Spacer(modifier = Modifier.height(12.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(start = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(model = R.drawable.icon_community2, contentDescription = "")

                Spacer(modifier = Modifier.width(4.dp))
                ///COMMUNITY NAME
                Text(
                    text = "Fandawara Community",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)) {
                //COMUNITY PROFILE
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 12.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    //COMUNITY Photo
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        model = R.drawable.dummy_community_profile,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    ///COMMUNITY NAME
                    Text(
                        text = "Fandawara", style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Activity Detail
                    Text(
                        modifier = Modifier.padding(end = 24.dp),
                        text = "Stay informed, stay inspired, and join us in shaping a brighter future for our planet. We invite you to explore our diverse range of upcoming events and activities, each thoughtfully designed to foster collaboration, inspire action, and drive meaningful change. Whether you're passionate about sustainability, eager to learn new ways to make a difference, or simply looking to connect with like-minded individuals, our initiatives provide an opportunity for everyone to get involved. Together, we can take meaningful steps toward creating a cleaner, greener future—one where communities thrive,",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(end = 24.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Color.White
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(2.dp)
                                .clip(
                                    RoundedCornerShape(24.dp)
                                ),
                            model = R.drawable.dummy_community_activity,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Divider(
                modifier = Modifier. padding(horizontal = 24.dp),
                thickness = 1.dp
            )
        }
    }
}