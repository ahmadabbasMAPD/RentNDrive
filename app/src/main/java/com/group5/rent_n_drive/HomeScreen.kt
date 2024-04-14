package com.group5.rent_n_drive

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.Button
//import androidx.compose.material.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.group5.rent_n_drive.datastore.userDatastore
import kotlinx.coroutines.launch

//REFERENCES
// https://developer.android.com/develop/ui/compose/graphics/draw/overview
// https://stackoverflow.com/questions/67875360/stack-images-one-over-the-other-z-axis-in-android
// https://developer.android.com/develop/ui/compose/layouts


@Composable
fun HomeScreen(navController: NavController) {
    val appScope = rememberCoroutineScope()
    val categories = listOf("Compact", "Sport", "Sedan", "SUV")
    val carsByCategory = categories.map { category ->
        cars.filter { it.type == category }
    }
    val userDatastoreRef = userDatastore(LocalContext.current)
    val userName = userDatastoreRef.getUserName.collectAsState(initial = "")
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(modifier = Modifier.padding(20.dp), onClick = {}) {
            Text(text = "Welcome ${userName.value} !")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            categories.forEachIndexed { index, category ->
                Spacer(modifier = Modifier.height(5.dp))
                Canvas(modifier = Modifier
                    .height(10.dp).fillMaxWidth()) {
                    drawLine(
                        start = Offset(x = size.width, y = 0f),
                        end = Offset(x = 0f, y = 1f),
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                CategoryHeader(category)
                LazyRow(
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(carsByCategory[index]) { car ->
                        CarCard(car = car, onCarClick = { selectedCar ->
                            // Navigate to the booking screen with the selected car
                            appScope.launch{
                                userDatastoreRef.saveCarInformation(selectedCar.id,"")
                            }
                            navController.navigate("booking")
                        })
                    }
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CarCard(car: Car, onCarClick: (Car) -> Unit) {

    val infiniteAnimation = rememberInfiniteTransition(label = "infinite_1_part_1")

    val infiniteRotation by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infinite_1_part_2"
    )
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clickable { onCarClick(car) }, // Make the card clickable
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(115.dp)) {
                drawRect(
                    color = Color.Gray
                )
            }
            Canvas(modifier = Modifier.size(40.dp)
                .rotate(infiniteRotation)
            ) {
                drawCircle(
                    color = Color.White,
                    center = center,
                    radius = size.minDimension / 2,
                    style = Stroke(width = 4.dp.toPx())
                )
                drawLine(
                    start = Offset(x = size.width, y = 0f),
                    end = Offset(x = 0f, y = size.height),
                    color = Color.White
                )
            }
            Image(
                painter = rememberImagePainter(data = car.imageUrl),
                contentDescription = car.name,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = car.name, color = Color.Black)
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}
