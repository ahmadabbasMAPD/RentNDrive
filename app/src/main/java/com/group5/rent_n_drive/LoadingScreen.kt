package com.group5.rent_n_drive

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group5.rent_n_drive.datastore.userDatastore
import kotlinx.coroutines.launch

@Composable
fun LoadingScreen(navCon: NavController, destination: String) {
    val appScope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val userDatastoreRef = userDatastore(LocalContext.current)//(context)

    val datastoreUserPassword = userDatastoreRef.getUserPassword.collectAsState(initial = "")
    val datastoreUserName = userDatastoreRef.getUserName.collectAsState(initial = "")

    Column(modifier = Modifier
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Canvas(modifier = Modifier
            .height(200.dp)
            .width(200.dp)) {
            drawCircle(
                color = Color.Blue,
                center = center,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}