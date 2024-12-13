package com.example.brainybunch.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.brainybunch.R

@Composable
fun RowArticleItem(

) {
    Box(
    modifier = Modifier
        .width(250.dp)
        .height(170.dp)
        .border(1.dp, Color.White, RoundedCornerShape(24.dp))
        .clip(RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((170 * 0.6).dp),
                model = R.drawable.dummy_article_photo2,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.width(160.dp),
                    text = "Inovasi Pengolahan Sampah Melalui Resappan Biopori untuk Meningkatkan Kesuburan Tanah dan Mengurangi Limbah", style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(3.dp))

                AsyncImage(modifier = Modifier.size(24.dp),model = R.drawable.icon_views, contentDescription = "")
                //VIEWS
                Text(
                    text = "23", style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(3.dp))

                AsyncImage(modifier = Modifier.size(36.dp),model = R.drawable.icon_bookmark, contentDescription = "")

            }
        }
    }
    
    Spacer(modifier = Modifier.width(12.dp))
}