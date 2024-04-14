import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.group5.rent_n_drive.Car
import com.group5.rent_n_drive.MyImage
import java.util.Calendar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookingScreen(navCon: NavController, car: Car, onBook: (Car, String, String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    val context = LocalContext.current
    val navController = rememberNavController()

    // Determine if the "Book Now" button should be enabled
    val isBookingEnabled = selectedDate.isNotEmpty() && selectedTime.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Details", fontWeight = FontWeight.Bold) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 4.dp,
                navigationIcon = {
                    IconButton(onClick = { navCon.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyImage(imageUrl = car.imageUrl)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Car: ${car.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Type: ${car.type}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Select Date and Time", color = Color.Gray, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showDatePicker(context) { date -> selectedDate = date } }) {
                    Text("Select Date")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showTimePicker(context) { time -> selectedTime = time } }) {
                    Text("Select Time")
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Display selected date and time
                Text(text = "Selected Date: $selectedDate", fontSize = 16.sp)
                Text(text = "Selected Time: $selectedTime", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onBook(car, selectedDate, selectedTime) }, enabled = isBookingEnabled) {
                    Text("Book Now", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    )
}

private fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Calculate the current date and time in milliseconds
    val currentDateInMillis = System.currentTimeMillis()

    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
        val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
        onDateSelected(selectedDate)
    }, year, month, day).apply {
        // Set the minimum date to the current date
        datePicker.minDate = currentDateInMillis
    }.show()
}

private fun showTimePicker(context: Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(context, { _, selectedHour, selectedMinute ->
        val selectedTime = "$selectedHour:$selectedMinute"
        onTimeSelected(selectedTime)
    }, hour, minute, true).show()
}