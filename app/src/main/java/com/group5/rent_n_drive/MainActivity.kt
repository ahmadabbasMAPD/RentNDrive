package com.group5.rent_n_drive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.group5.rent_n_drive.ui.theme.RentNDriveTheme
import com.group5.rent_n_drive.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentNDriveTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        // AppBar with search functionality
                        val searchState = remember { mutableStateOf(TextFieldValue()) }
                        TopAppBar(
                            title = { Text("Rent N Drive") },
                            actions = {
                                IconButton(onClick = { /* Handle search action */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        )
                        TextField(
                            value = searchState.value,
                            onValueChange = { searchState.value = it },
                            label = { Text("Search") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        // HomeScreen composable
                        HomeScreen()
                    }
                }
            }
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
