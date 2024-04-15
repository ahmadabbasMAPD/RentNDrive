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
/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */

//REFERENCES
//https://developer.android.com/develop/ui/compose/graphics/draw/overview


// This is a temporary screen used to give illusion of processing payment
@Composable
fun LoadingScreen(navCon: NavController, destination: String) {
    LaunchedEffect(Unit) {
        delay(4000)  // the delay of 3 seconds
        navCon.navigate(destination)
    }
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite_1_part_1")

    val infiniteRotation by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infinite_1_part_2"
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
            .rotate(infiniteRotation)
        ) {
            drawCircle(
                color = Color.Blue,
                center = center,
                radius = size.minDimension / 2.1f,
                style = Stroke(width = 7.dp.toPx())
            )
            drawRect(
                color = Color.White,
                size = size / 2F

            )

            drawCircle(
                color = Color.Black,
                center = center,
                radius = size.minDimension / 3f,
            )
            drawCircle(
                color = Color.Gray,
                center = center,
                radius = size.minDimension / 4.6f,
            )

            drawCircle(
                color = Color.LightGray,
                center = Offset(center.x + 40f, center.y),
                radius = size.minDimension / 10f,
            )

            drawCircle(
                color = Color.LightGray,
                center = Offset(center.x - 40f, center.y),
                radius = size.minDimension / 10f,
            )

            drawCircle(
                color = Color.LightGray,
                center = Offset(center.x , center.y+ 40f),
                radius = size.minDimension / 10f,
            )

            drawCircle(
                color = Color.LightGray,
                center = Offset(center.x , center.y- 40f),
                radius = size.minDimension / 10f,
            )

            drawCircle(
                color = Color.Gray,
                center = center,
                radius = size.minDimension / 12f,
            )

            drawLine(
                start = Offset(x = 7f, y = size.height /1.92f),
                end = Offset(x = 7f - 100f, y = (size.height /1.92f) + 100f),
                color = Color.Blue,
                strokeWidth = 18f
            )
            drawLine(
                start = Offset(x = 15f, y = size.height /1.92f),
                end = Offset(x = 15f + 100f, y = (size.height /1.92f) + 100f),
                color = Color.Blue,
                strokeWidth = 18f
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "LOADING...")
    }
}