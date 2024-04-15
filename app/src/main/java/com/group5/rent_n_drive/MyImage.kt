package com.group5.rent_n_drive
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun MyImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null, // Provide a meaningful description for accessibility
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop // Scale the image to fill the bounds, cropping if necessary
    )
}
