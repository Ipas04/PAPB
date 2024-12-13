package com.example.brainybunch.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun MyButton(
    modifier: Modifier,
    onClick:()->Unit,
    text:String
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp), onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3F4E3E),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }

}