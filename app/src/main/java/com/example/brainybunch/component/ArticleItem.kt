package com.example.brainybunch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.brainybunch.R

@Composable
fun ArticleItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
    ) {
        Row(Modifier.fillMaxSize()) {
            //ARTTICLE PHOTO
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = R.drawable.dummy_article_photo,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            //Judul dan Tanggal
            Column(modifier = Modifier.width(180.dp))  {
                //JUDUL
                Text(
                    text = "Mengelola Sampah: Langkah Kecil untuk Masa Depan yang Lebih Hijau", style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    AsyncImage(modifier = Modifier.size(24.dp), model = R.drawable.icon_date, contentDescription = "")
                    Spacer(modifier = Modifier.width(4.dp))
                    //tanggal
                    Text(
                        text = "Sep 29, 2023", style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Left,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier
                        .width(12.dp)
                        .height(3.dp)
                        .background(Color(0xFF3F4E3E).copy(alpha = 0.7f)))
                    Spacer(modifier = Modifier.weight(1f))

                    //DURASI BACA
                    Text(
                        text = "5 min read", style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Left,
                        color = Color.Gray
                    )

                }

            }

            Spacer(modifier = Modifier.width(8.dp))
            //BOOKMARK
            AsyncImage(modifier = Modifier.size(36.dp), model = R.drawable.icon_bookmark, contentDescription = "")
        }
        Divider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.Black,
            thickness = 1.dp
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}