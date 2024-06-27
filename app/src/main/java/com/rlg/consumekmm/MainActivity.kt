package com.rlg.consumekmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rlg.consumekmm.ui.theme.ConsumeKMMTheme
import com.rlg.play_kotlin_multi_plat.Greeting
import com.rlg.play_kotlin_multi_plat.MyScopedClass
import com.rlg.play_kotlin_multi_plat.SharedVMState
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mySingletonClass: MyScopedClass by inject()
    private val viewModel: AndroidSharedViewModel = AndroidSharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val hi = Greeting().greet()
        println("The output is: $hi")
        println("Say Hello koin is: ${mySingletonClass.sayHello()}")

        setContent {
            val state = rememberStateWithFlow(viewModel.state, SharedVMState())

            ConsumeKMMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        data = state.data,
                        isLoading = state.isLoading,
                        error = state.error,
                        counter = state.counter,
                        onFetchData = { viewModel.fetchData() },
                        onIncrementCounter = { viewModel.incrementCounter() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    data: String,
    isLoading: Boolean,
    error: Throwable?,
    counter: Int,
    onFetchData: () -> Unit,
    onIncrementCounter: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (isLoading) {
            Text(text = "Loading...")
        } else if (error != null) {
            Text(text = "Error: ${error.message}")
        } else {
            Text(text = "Data: $data")
        }
        Text(text = "Counter: $counter")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onFetchData) {
            Text(text = "Fetch Data")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onIncrementCounter) {
            Text(text = "Increment Counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ConsumeKMMTheme {
        MainScreen(
            data = "Preview Data",
            isLoading = false,
            error = null,
            counter = 0,
            onFetchData = {},
            onIncrementCounter = {}
        )
    }
}