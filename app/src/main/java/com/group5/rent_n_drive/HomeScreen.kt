package com.group5.rent_n_drive
import android.annotation.SuppressLint
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.group5.rent_n_drive.datastore.UserBookingDatastore
import kotlinx.coroutines.launch

/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController) {
    val appScope = rememberCoroutineScope()
    val categories = listOf("Compact", "Sport", "Sedan", "SUV")//set list of Categories
    val categoryPrices = listOf(1500, 4500, 2500, 3000)//set prices for each category
    val carsByCategory = categories.map { category ->
        cars.filter { it.type == category }
    }
    val userDatastoreRef = UserBookingDatastore(LocalContext.current)//get Datastore information to display current booking
    val userName = userDatastoreRef.getUserName.collectAsState(initial = "")
    val startDate = userDatastoreRef.getCarStartDate.collectAsState(initial = "")
    val endDate = userDatastoreRef.getCarEndDate.collectAsState(initial = "")
    val carId = userDatastoreRef.getCarId.collectAsState(initial = 0)
    val prevUser = userDatastoreRef.getPreviousUser.collectAsState(initial = "")
    val price = userDatastoreRef.getCarPrice.collectAsState(initial = 0)
    val isBooked = userDatastoreRef.getIsBookingMade.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Welcome ${userName.value} !", color = Color.White) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 4.dp,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if(isBooked.value!!){
                    val bookedCar = cars.find { it.id == carId.value }//check if car exists
                    if(bookedCar != null && prevUser.value!! == userName.value!!) {//check if current user same as the
                        // previous user to display prior booking history
                        Text(//display current booking history
                            text = "Current Booking: ${bookedCar.name} from ${startDate.value} to ${endDate.value} for $ ${price.value}.00",
                            modifier = Modifier.padding(top = 16.dp),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            appScope.launch { userDatastoreRef.clearCarBookingInformation() }
                            navController.navigate("login")
                        }) {
                            Text(text = "Cancel Booking")//cancels booking and sends back to login screen
                        }
                    }else{
                        appScope.launch { userDatastoreRef.clearCarBookingInformation() }
                        Text(text  = "No Booking Made")
                    }
                }else{
                    Text(text  = "No Booking Made")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 8.dp, bottom = 16.dp)
                ) {
                    categories.forEachIndexed { index, category ->//Display each car segmented by Category
                        Spacer(modifier = Modifier.height(5.dp))
                        CategoryHeader("$category - $ ${categoryPrices[index]}.00")//Show Price of each Category
                        LazyRow(
                            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            items(carsByCategory[index]) { car ->
                                CarCard(car = car, onCarClick = { selectedCar ->
                                    appScope.launch {
                                        userDatastoreRef.saveCarInformation(selectedCar.id, categoryPrices[index])
                                    }
                                    navController.navigate("booking")
                                })
                            }
                        }
                    }
                }
            }
        }
    )
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
@OptIn(ExperimentalCoilApi::class)//to display each Image Tile
@Composable
fun CarCard(car: Car, onCarClick: (Car) -> Unit) {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite_1_part_1")
    val infiniteRotation by infiniteAnimation.animateFloat(//Image Loading graphic and animation
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "infinite_1_part_2"
    )
    Column(
        modifier = Modifier
            .width(200.dp) // Increased width to make the card bigger
            .padding(8.dp)
            .clickable { onCarClick(car) }
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier //Image Loading graphic and animation
                .size(60.dp) // Increased size to make the canvas bigger
                .rotate(infiniteRotation)
            ) {
                drawCircle(
                    color = Color.Black,
                    center = center,
                    radius = size.minDimension / 2,
                    style = Stroke(width = 4.dp.toPx())
                )

                drawCircle(
                    color = Color.Black,
                    center = center,
                    radius = size.minDimension / 5,
                )
                val offsetValue = 15f
                drawLine(
                    start = Offset(x = size.width - offsetValue, y = offsetValue),
                    end = Offset(x = offsetValue, y = size.height - offsetValue),
                    color = Color.Black,
                    strokeWidth = 10f
                )

                drawLine(
                    start = Offset(x = size.width / 2f, y = size.height / 2f),
                    end = Offset(x = size.width - offsetValue, y = size.height - offsetValue),
                    color = Color.Black,
                    strokeWidth = 10f
                )
            }
            Image(
                painter = rememberImagePainter(data = car.imageUrl),
                contentDescription = car.name,
                modifier = Modifier.size(150.dp), // Increased size to make the image bigger
                contentScale = ContentScale.Fit
            )
        }
        Text(text = car.name, color = Color.Black, fontSize = 20.sp)
    }
}
