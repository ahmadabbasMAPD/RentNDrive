package com.group5.rent_n_drive

import android.app.NotificationManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group5.rent_n_drive.datastore.UserBookingDatastore
import kotlinx.coroutines.launch

// REFERENCES
// https://developer.android.com/develop/ui/compose/graphics/draw/overview
// https://developer.android.com/reference/android/app/NotificationManager

@Composable
fun LoginScreen(onLogin: (String, String) -> Unit) {
    val appScope = rememberCoroutineScope() //set up app scope
    val context = LocalContext.current //get local context
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val orange = Color(0xFFe18b1e)//set new color for logo
    val userDatastoreRef = UserBookingDatastore(LocalContext.current)//(context)
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager//use
    //to check whether notification permissions are allowed

    Column(modifier = Modifier
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally//align elements in center
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(modifier = Modifier
            .height(200.dp)
            .width(200.dp)) {
            Canvas(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            ) {

                //Below are the prices to draw the Rent N Drive Logo
                val offsetValue = 50f//Used to move lines around
                drawLine(
                    start = Offset(x = size.width / 2f, y = size.height / 2f),
                    end = Offset(x = size.width - offsetValue, y = size.height - offsetValue),
                    color = orange,
                    strokeWidth = 50f
                )
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

                drawCircle(
                    color = Color.LightGray,
                    center = center,
                    radius = size.minDimension / 7,
                )

                drawLine(
                    start = Offset(x = size.width - offsetValue, y = offsetValue),
                    end = Offset(x = offsetValue, y = size.height - offsetValue),
                    color = Color.Black,
                    strokeWidth = 30f
                )


            }
            Text(text = "R N D" ,fontSize = 30.sp,  color = orange)
        }
        if(!notificationManager.areNotificationsEnabled()){
            //send toast to ask for permission if permission not given for app.
            Toast.makeText(context, "This App requires Notification Permission, please allow Notifications", Toast.LENGTH_LONG).show()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Rent and Drive Login")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),//hide password using ***
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            appScope.launch {  userDatastoreRef.saveUserInformation(username) }//save the user name
            onLogin(username, password)//Login
        }) {
            Text("Login")
        }
    }
}
