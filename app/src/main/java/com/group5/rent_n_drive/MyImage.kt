package com.group5.rent_n_drive
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.group5.rent_n_drive.Car

@Composable
fun MyImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null, // Provide a meaningful description for accessibility
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop // Scale the image to fill the bounds, cropping if necessary
    )
}
