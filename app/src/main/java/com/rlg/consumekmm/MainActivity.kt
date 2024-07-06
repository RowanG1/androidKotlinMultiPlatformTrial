package com.rlg.consumekmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rlg.consumekmm.ui.theme.ConsumeKMMTheme
import com.rlg.play_kotlin_multi_plat.domain.viewmodels.SharedVMState
import com.rlg.play_kotlin_multi_plat.getPlatform
import com.rlg.play_kotlin_multi_plat.ui.SharedUI.FirstUI

class MainActivity : ComponentActivity() {
    private val viewModel: AndroidSharedViewModel = AndroidSharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

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
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = getPlatform().name, modifier = Modifier.padding(bottom = 24.dp), style = MaterialTheme.typography.headlineLarge)

        }
        if (isLoading) {
            Text(text = "Loading...")
        } else if (error != null) {
            Text(text = "Error: ${error.message}")
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Data: $data")
                Button(onClick = onFetchData) {
                    Text(text = "Fetch Data")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Counter: $counter")
            Button(onClick = onIncrementCounter) {
                Text(text = "Increment Counter")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        FirstUI()
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