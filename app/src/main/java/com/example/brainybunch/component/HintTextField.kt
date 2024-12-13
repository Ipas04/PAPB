package com.example.brainybunch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.ui.theme.poppinsFontFamily

@Composable
fun HintTextField(
    hint: String,
    onValueChange: (String) -> Unit,
    value: String,
) {
    val black = Color(0xFF323142)
    val darkGrey = Color(0xFFACADB9)

    var color by remember{
      mutableStateOf(black)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(24.dp))
            AsyncImage(modifier = Modifier, model = R.drawable.icon_email, contentDescription = "")
            Box(modifier = Modifier.fillMaxWidth()){
                if (value.isEmpty()) {
                    color = darkGrey
                    Text(
                        modifier= Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
                        text = hint,
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkGrey
                    )
                } else{
                    color = black
                }
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp),
                    value = value,
                    onValueChange = onValueChange,
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontFamily = poppinsFontFamily,
                        color = black
                    ),

                    )
            }

        }
    }

}