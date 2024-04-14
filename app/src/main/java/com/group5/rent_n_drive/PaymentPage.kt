package com.group5.rent_n_drive

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group5.rent_n_drive.datastore.UserBookingDatastore
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentPage(navCon: NavController) {
    var cardNumber by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    val appScope = rememberCoroutineScope()
    val userDatastoreRef = UserBookingDatastore(LocalContext.current)
    val userName = userDatastoreRef.getUserName.collectAsState(initial = "")

    // Validation functions
    val isCardNumberValid = cardNumber.length == 16
    val isCvvValid = cvv.length in 3..4
    val isExpiryDateValid = try {
        val parts = expiryDate.split("/")
        val month = parts[0].toInt()
        val year = parts[1].toInt()
        val currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        year > currentYear || (year == currentYear && month >= currentMonth)
    } catch (e: Exception) {
        false
    }

    // Determine if the "Pay Now" button should be enabled
    val isPaymentEnabled = cardHolderName.isNotEmpty() && isCardNumberValid && isCvvValid && isExpiryDateValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment Details") },
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    value = cardHolderName,
                    onValueChange = { cardHolderName = it },
                    label = {
                        androidx.compose.material3.Text(
                            text = "CARD HOLDER NAME",
                            color = Color.Blue
                        )
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = {
                        androidx.compose.material3.Text(
                            text = "CARD NUMBER",
                            color = Color.Blue
                        )
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = {
                        androidx.compose.material3.Text(
                            text = "EXPIRY DATE (MM/YY)",
                            color = Color.Blue
                        )
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    value = cvv,
                    onValueChange = { cvv = it },
                    label = {
                        androidx.compose.material3.Text(
                            text = "CVV",
                            color = Color.Blue
                        )
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    val newDestination = "confirmation"
                    appScope.launch{
                        userDatastoreRef.savePreviousUserInformation(userName.value!!)
                    }
                    navCon.navigate("loading/${newDestination}")
                }, enabled = isPaymentEnabled) {
                    Text("PAY NOW")
                }
            }
        }
    )
}
