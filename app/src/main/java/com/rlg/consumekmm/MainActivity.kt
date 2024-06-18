package com.rlg.consumekmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rlg.consumekmm.ui.theme.ConsumeKMMTheme
import com.rlg.play_kotlin_multi_plat.Greeting
import com.rlg.play_kotlin_multi_plat.MyScopedClass
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mySingletonClass: MyScopedClass by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val hi = Greeting().greet()
        println("The output is: ${hi}")
        println("Say Hello koin is: ${mySingletonClass.sayHello()}")

        setContent {
            ConsumeKMMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConsumeKMMTheme {
        Greeting("Android")
    }
}