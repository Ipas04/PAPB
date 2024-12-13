package com.example.brainybunch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItem() {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(100.dp))
        .background(Color(0xFFD9D9D9).copy(alpha = 0.2f))){
        Text(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            text = "Olah Sampah", style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            textAlign = TextAlign.Left,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
    
    Spacer(modifier = Modifier.width(12.dp))
}