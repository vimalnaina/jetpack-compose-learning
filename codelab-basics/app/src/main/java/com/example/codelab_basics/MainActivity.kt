package com.example.codelab_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelab_basics.ui.theme.CodelabBasicsTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodelabBasicsTheme {
                    MyApp(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, names: List<String> = listOf("World", "Vimal!")) {

    var shouldShowOnBoard by remember { mutableStateOf(true) }

    Surface(modifier) {
        Column(modifier) {
            if (shouldShowOnBoard) {
                OnBoardingScreen(onClick = {shouldShowOnBoard = false})
            } else {
                Greetings()
            }
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, names: List<String> = listOf("World!", "Vimal!")) {
    Column {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val isExpanded = remember { mutableStateOf(false) }
    val extraPadding = if (isExpanded.value) 46.dp else 0.dp
    Surface (color = MaterialTheme.colorScheme.primary, modifier = modifier
        .fillMaxWidth()
        .padding(2.dp),) {
        Row(modifier = modifier.padding(24.dp)) {
            Column(modifier = modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(
                    text = "Hello",
                )
                Text(
                    text = "$name!",
                )
            }

            ElevatedButton(onClick = {isExpanded.value = !isExpanded.value}) {
                Text(if (isExpanded.value) "Hide" else "Show")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyPreview() {
    CodelabBasicsTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,) {
        Text("Welcome to the Basics Codelab!")
        ElevatedButton(onClick = onClick, modifier = Modifier.padding(vertical = 24.dp)) {
            Text("Continue")
        }
    }
}