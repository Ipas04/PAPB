package com.example.brainybunch.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyOutlinedButton(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,  // Transparent fill
            contentColor = Color(0xFF3F4E3E)           // Outline color
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color(0xFF3F4E3E) )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }

}