package com.group5.rent_n_drive

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
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
// https://developer.android.com/develop/ui/compose/graphics/draw/overview

class Notifier: ViewModel(){
    fun sendNotification(context: Context, carName: String) {
        //val CHANNEL_ID = "BOOKINGCONFRIMATIONRND"
        createNotificationChannel(context, "BOOKINGCONFRIMATIONRND")
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notice = NotificationCompat.Builder(context, "BOOKINGCONFRIMATIONRND")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Booking Confirmed!")
            .setContentText("$carName Has been Booked")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true).build()

        notificationManager.notify(1,notice)

    }
    private fun createNotificationChannel(context: Context, notifyChannelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "RND Booking"
            val descriptionText = "Booking Confirmed"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(notifyChannelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

@Composable
fun ConfirmationScreen(car: Car, navCon: NavController) {
    val context = LocalContext.current

    val notificationObject = Notifier()
    notificationObject.sendNotification(context, car.name)

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Canvas(modifier = Modifier.height(200.dp).width(200.dp)) {
            drawCircle(
                color = Color.Green,
                center = center,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
            rotate(degrees = 45f) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                    size = size / 3F
                )
            }
            rotate(degrees = -45f) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                    size = size / 3F
                )
            }
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

