package com.interneted.common

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.interneted.androidcommon.net.NetworkUtil
import com.interneted.common.ui.theme.CommonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val rememberCoroutineScope = rememberCoroutineScope()
                        var rawData by remember {
                            mutableStateOf("")
                        }

                        Greeting("Android app")
                        TextButton(onClick = {
                            rememberCoroutineScope.launch {
                                rawData = NetworkUtil.getData("https://google.com")
                            }
                        }) {
                            Text(text = "Click me")
                        }
                        Text(text = rawData)

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CommonTheme {
        Greeting("Android")
    }
}