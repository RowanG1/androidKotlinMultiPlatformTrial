package com.rlg.consumekmm

import androidx.lifecycle.ViewModel
import com.rlg.play_kotlin_multi_plat.domain.viewmodels.SharedVMState
import com.rlg.play_kotlin_multi_plat.domain.viewmodels.SharedViewModel
import com.rlg.play_kotlin_multi_plat.utils.CommonFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidSharedViewModel : ViewModel(), KoinComponent {
  private val sharedViewModel: SharedViewModel by inject()
  val state: CommonFlow<SharedVMState> = sharedViewModel.state

  fun fetchData() {
    val result = sharedViewModel.fetchData(onError = {})
  }

  fun incrementCounter() {
    sharedViewModel.incrementCounter()
  }
}
