package com.rlg.consumekmm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rlg.play_kotlin_multi_plat.utils.CommonFlow

@Composable
fun <T> rememberStateWithFlow(flow: CommonFlow<T>, initialValue: T): T {
    var state by remember { mutableStateOf(initialValue) }

    DisposableEffect(flow) {
        val closeable = flow.watch { newState ->
            state = newState
        }

        onDispose {
            closeable.close()
        }
    }

    return state
}
