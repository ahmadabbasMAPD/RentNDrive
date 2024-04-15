package com.group5.rent_n_drive

import BookingScreen
import androidx.activity.compose.setContent

//
//import androidx.activity.ComponentActivity
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.tooling.preview.Preview
//import com.group5.rent_n_drive.ui.theme.RentNDriveTheme
//import com.group5.rent_n_drive.HomeScreen

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            RentNDriveTheme {
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Column {
//                        // AppBar with search functionality
//                        val searchState = remember { mutableStateOf(TextFieldValue()) }
//                        TopAppBar(
//                            title = { Text("Rent N Drive") },
//                            actions = {
//                                IconButton(onClick = { /* Handle search action */ }) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_search),
//                                        contentDescription = "Search"
//                                    )
//                                }
//                            }
//                        )
//                        TextField(
//                            value = searchState.value,
//                            onValueChange = { searchState.value = it },
//                            label = { Text("Search") },
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        // HomeScreen composable
//                        HomeScreen()
//                    }
//                }
//            }
//        }
//    }
//}






import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group5.rent_n_drive.datastore.UserBookingDatastore
import com.group5.rent_n_drive.ui.theme.RentNDriveTheme

/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentNDriveTheme {
                AppNavigator()
            }
        }
    }
}


@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {//Set up Navigation with Login as "Splash page"
        composable("login") {//Rest of pages given below
            LoginScreen(onLogin = { username, password ->if (username.trim() != "" && password.trim() != "") {
            navController.navigate("home")//make sure iusername and password is not empty
        }}) }
        composable("home") { HomeScreen(navController = navController) }

        composable("confirmation") {
            val userDatastoreRef = UserBookingDatastore(LocalContext.current)//(context)
            val carId = userDatastoreRef.getCarId.collectAsState(initial = 0)
            val car = cars.find { it.id == carId.value }//Double Check if car exits
            if (car != null) ConfirmationScreen(car = car, navCon = navController)
        }
        composable("booking") {
            val userDatastoreRef = UserBookingDatastore(LocalContext.current)//(context)
            val carId = userDatastoreRef.getCarId.collectAsState(initial = 0)
            val car = cars.find { it.id == carId.value }
            if (car != null) BookingScreen(navCon = navController,car = car)
        }
        composable("payment"){
            PaymentPage(navCon = navController)
        }

        composable("loading/{destination}"){backStackEntry ->//get value form path to fetch next destination
            val destination = backStackEntry.arguments?.getString("destination")
            if(destination != null) LoadingScreen(navCon = navController, destination = destination)
        }

        composable("captureCar"){
            CarImageCapture(navCon = navController)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RentNDriveTheme {
        // Your preview code here
    }
}
