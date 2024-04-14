package com.group5.rent_n_drive

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


//REFERENCES
//
@Composable
fun LoadingScreen(navCon: NavController, destination: String) {
    LaunchedEffect(Unit) {
        delay(4000)  // the delay of 3 seconds
        navCon.navigate(destination)
    }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite_part_1")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infinite_part_2"
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Canvas(modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .rotate(rotation)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = Color.Blue,
                center = center,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
            drawLine(
                start = Offset(x = canvasWidth, y = 0f),
                end = Offset(x = 0f, y = canvasHeight),
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "LOADING...")
    }
}