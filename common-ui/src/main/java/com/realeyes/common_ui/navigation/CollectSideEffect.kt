package com.realeyes.common_ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@Composable
fun <T:Any> CollectSideEffect(
    effect: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    context: CoroutineContext = Dispatchers.Main.immediate,
    onSideEffect: suspend CoroutineScope.(effect: T) -> Unit
) {
    LaunchedEffect(effect, LocalLifecycleOwner.current) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(context) {
                effect.collect { onSideEffect(it) }
            }
        }
    }
}
