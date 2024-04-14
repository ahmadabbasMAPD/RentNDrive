import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.group5.rent_n_drive.Car
import com.group5.rent_n_drive.MyImage
import androidx.compose.foundation.Image




@Composable
fun BookingScreen(car: Car, onBook: (Car, String, String) -> Unit) {
//fun BookingScreen(car: Car){
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Booking Details", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        MyImage(imageUrl = car.imageUrl) // Use the MyImage composable for displaying the car image
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Car: ${car.name}")
        Text(text = "Type: ${car.type}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Select Date and Time", color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = true }) {
            Text("Select Date and Time")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onBook(car, selectedDate, selectedTime) }) {
            Text("Book Now")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select Date and Time") },
            text = {
                Column {
                    // Placeholder for date and time selection
                    Text("Date: $selectedDate")
                    Text("Time: $selectedTime")
                }
            },
            confirmButton = {
                Button(onClick = {
                    // Implement logic to update selectedDate and selectedTime
                    // For example, you might use a date picker and time picker here
                    // Once selected, update the state variables accordingly
                    showDialog = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
