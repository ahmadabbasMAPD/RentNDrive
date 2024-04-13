package com.group5.rent_n_drive

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group5.rent_n_drive.ui.theme.RentNDriveTheme

// Import for LoginScreen, HomeScreen, BookingScreen, and ConfirmationScreen
import com.group5.rent_n_drive.LoginScreen
import com.group5.rent_n_drive.HomeScreen
import com.group5.rent_n_drive.BookingScreen
import com.group5.rent_n_drive.ConfirmationScreen

// Import for Car data class
import com.group5.rent_n_drive.Car

// Import for navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Import for Modifier and other UI components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.input.TextField

// Import for navigation arguments
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

// Import for preview
//import androidx.compose.ui.tooling.preview.Preview

// Your MainActivity and AppNavigator composable code goes here


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
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(onLogin = { username, password ->if (username == "test" && password == "password") {
            navController.navigate("home")
        } else {
            // Show error message

        } }) }
        composable("home") { HomeScreen(navController = navController) }
        composable("booking/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull()
            val car = cars.find { it.id == carId }
            if (car != null) BookingScreen(car = car, onBook = { /* Handle booking */ })
        }
        composable("confirmation/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull()
            val car = cars.find { it.id == carId }
            if (car != null) ConfirmationScreen(car = car)
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
