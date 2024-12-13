package com.example.brainybunch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
fun CommunityProfileItem(){
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(110.dp)
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            //COMUNITY PROFILE
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                //COMUNITY Photo
                AsyncImage(
                    modifier = Modifier
                        .size(66.dp)
                        .clip(CircleShape),
                    model = R.drawable.dummy_community_profile,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            //Comunity Name
            Text(
                text = "Fandawara", style = MaterialTheme.typography.bodyMedium,
                fontSize = 10.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(3.dp))

            //Comunity Followers
            Text(
                text = "240rb anggota", style = MaterialTheme.typography.bodyMedium,
                fontSize = 10.sp,
                textAlign = TextAlign.Justify,
                color = Color.White
            )
        }
    }
    
    Spacer(modifier = Modifier.width(12.dp))
}