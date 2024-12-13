package com.example.brainybunch.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeader(
    text:String,
    onBackClick:() ->Unit
) {
    Card(
    modifier = Modifier
    .fillMaxWidth()
    .height(64.dp),
    shape = RoundedCornerShape(0.dp),
    colors = CardDefaults.cardColors(Color(0xFF3F4E3E)),
    elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text, style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Icon(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}