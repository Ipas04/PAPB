package com.example.brainybunch.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrackingWasteProgress(data: List<Float>) {
    val maxValue = (data.maxOrNull() ?: 0f) + 1f // Add padding for the maximum value
    val numberOfWeeks = data.size

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Tracking Waste Progress",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF4A4A4A)
            )

            // Line Chart
            Box(
                modifier = Modifier
                    .height(75.dp)
                    .fillMaxWidth()
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val stepX = size.width / (numberOfWeeks - 1)
                    val stepY = size.height / maxValue

                    // Draw Y-Axis Labels
                    val yAxisStep = maxValue / 5
                    for (i in 1..5) { // Start from 1 to skip (0, 0)
                        val y = size.height - (i * stepY * yAxisStep)
                        drawLine(
                            color = Color.Gray.copy(alpha = 0.5f),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1f
                        )
                        drawContext.canvas.nativeCanvas.drawText(
                            "${(i * yAxisStep).toInt()}",
                            8f,
                            y,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = 24f
                            }
                        )
                    }

                    // Draw X-Axis Labels
                    for (i in 0..data.size + 1) { // Include data.size to show one additional week
                        val x = i * stepX
                        val weekLabel = if (i == data.size + 1) "W${data.size + 1}" else "W${i + 1}"
                        drawContext.canvas.nativeCanvas.drawText(
                            weekLabel,
                            x,
                            size.height + 24f,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = 24f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }

                    // Draw Axes
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 4f
                    )
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, size.height),
                        end = Offset(0f, 0f),
                        strokeWidth = 4f
                    )

                    // Draw Line Graph
                    for (i in 0 until data.size - 1) {
                        drawLine(
                            color = Color(0xFF4A4A4A),
                            start = Offset(i * stepX, size.height - (data[i] * stepY)),
                            end = Offset((i + 1) * stepX, size.height - (data[i + 1] * stepY)),
                            strokeWidth = 4f,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Bottom Stats
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Box(modifier = Modifier
                    .height(64.dp)
                    .weight(0.3f)
                    .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
                    .background(Color(0xFF3F4E3E)),
                    contentAlignment = Alignment.Center){
                    WasteStat(label = "0", value = "Points")
                }
                Spacer(modifier = Modifier.width(2.dp))

                Box(modifier = Modifier
                    .height(64.dp)
                    .weight(0.4f)
                    .background(Color(0xFF3F4E3E)),
                    contentAlignment = Alignment.Center){
                    WasteStat(label = "${(data.sum() / numberOfWeeks).toInt()} Kg avg.", value = "Saved Waste")
                }
                Spacer(modifier = Modifier.width(2.dp))

                Box(modifier = Modifier
                    .height(64.dp)
                    .weight(0.3f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp))
                    .background(Color(0xFF3F4E3E)),
                    contentAlignment = Alignment.Center){
                    WasteStat(label = "${data.sum().toInt()}", value = "Recycled")
                }

            }
        }
    }
}



@Composable
fun WasteStat(label: String, value: String) {
    Column(modifier = Modifier.padding(horizontal = 4.dp),horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.titleSmall, color = Color.White)
        Spacer(modifier = Modifier.height(4.dp))
        Text( text = value, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}