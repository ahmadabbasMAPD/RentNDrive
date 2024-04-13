package com.group5.rent_n_drive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BookingScreen(car: Car, onBook: (Car) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Booking Details", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Car: ${car.name}")
        Text(text = "Type: ${car.type}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onBook(car) }) {
            Text("Book Now")
        }
    }
}
