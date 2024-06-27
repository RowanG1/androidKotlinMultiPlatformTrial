package com.rlg.consumekmm

import androidx.lifecycle.ViewModel
import com.rlg.play_kotlin_multi_plat.SharedVMState
import com.rlg.play_kotlin_multi_plat.SharedViewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidSharedViewModel : ViewModel(), KoinComponent {
  private val sharedViewModel: SharedViewModel by inject()
  val state: StateFlow<SharedVMState> = sharedViewModel.state

  fun fetchData() {
    sharedViewModel.fetchData()
  }

  fun incrementCounter() {
    sharedViewModel.incrementCounter()
  }
}
