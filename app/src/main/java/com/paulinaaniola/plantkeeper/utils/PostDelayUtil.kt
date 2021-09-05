package com.paulinaaniola.plantkeeper.utils

import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.*

fun View.delayOnLifecycle(
    duration: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
    lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
        delay(duration)
        block()
    }
}