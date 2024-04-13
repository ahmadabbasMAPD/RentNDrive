package com.group5.rent_n_drive

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun HomeScreen(navController: NavController) {
    val categories = listOf("Compact", "Sport", "Luxury", "SUV")
    val carsByCategory = categories.map { category ->
        cars.filter { it.type == category }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        categories.forEachIndexed { index, category ->
            CategoryHeader(category)
            LazyRow(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(carsByCategory[index]) { car ->
                    CarCard(car = car, onCarClick = { selectedCar ->
                        // Navigate to the booking screen with the selected car
                        navController.navigate("booking/${selectedCar.id}")
                    })
                }
            }
        }
    }
}


@Composable
fun CategoryHeader(category: String) {
    Text(
        text = category,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
}

@Composable
fun CarCard(car: Car, onCarClick: (Car) -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clickable { onCarClick(car) }, // Make the card clickable
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = car.imageUrl),
            contentDescription = car.name,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = car.name, color = Color.Black)
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}
