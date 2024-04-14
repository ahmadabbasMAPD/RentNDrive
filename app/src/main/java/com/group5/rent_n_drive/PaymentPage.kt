package com.group5.rent_n_drive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PaymentPage(navCon: NavController) {

    var cardNumber by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var SCR by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = cardHolderName,
            onValueChange = { cardHolderName = it},
            label = { androidx.compose.material3.Text(
                text = "CARD HOLDER NAME",
                color = Color.Blue)
            },
            //visualTransfromation =
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = cardNumber,
            onValueChange = { cardNumber = it},
            label = {
                androidx.compose.material3.Text(
                    text = "CARD NUMBER",
                    color = Color.Blue
                )
            },
            //visualTransfromation =
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = SCR,
            onValueChange = { SCR = it},
            label = {
                androidx.compose.material3.Text(
                    text = "SECURITY CODE",
                    color = Color.Blue
                )
            },
            //visualTransfromation =
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val j = "confirmation"
            navCon.navigate("loading/${j}")
        }) {
            Text("PAY NOW")
        }
    }
}