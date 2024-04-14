package com.group5.rent_n_drive

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

//REFERENCES AND SOURCES:
// Class Example Notification Code
// Video: https://www.youtube.com/watch?v=LP623htmWcI
// Android Studio Developer: https://developer.android.com/develop/ui/views/notifications/build-notification#kts


class Notifier: ViewModel(){
    fun sendNotification(context: Context, carName: String) {
        //val CHANNEL_ID = "BOOKINGCONFRIMATIONRND"
        createNotificationChannel(context, "BOOKINGCONFRIMATIONRND")
        val intent = Intent()
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notice = NotificationCompat.Builder(context, "BOOKINGCONFRIMATIONRND")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Booking Confirmed!")
            .setContentText("$carName Has been Booked")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that fires when the user taps the notification.
            .setContentIntent(pendingIntent)
            .setAutoCancel(true).build()

        notificationManager.notify(1,notice)

    }
    private fun createNotificationChannel(context: Context, CHANNEL_ID: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Booking"
            val descriptionText = "Hello"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

@Composable
fun ConfirmationScreen(car: Car, navCon: NavController) {
    val context = LocalContext.current

    val c = Notifier()
    c.sendNotification(context, car.name)

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Canvas(modifier = Modifier.height(200.dp).width(200.dp)) {
            drawCircle(
                color = Color.Green,
                center = center,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Booking Confirmed!", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You have successfully booked ${car.name}.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navCon.navigate("home")
        }) {
            Text("Return To Home")
        }
    }
}

