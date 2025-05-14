package com.realeyes.common_ui.navigation

import androidx.navigation.NavController
import io.dolby.app.navigation.NavigationEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

class Navigator {
    private val navigationEventFlow =
        MutableSharedFlow<NavigationEvent>(extraBufferCapacity = Int.MAX_VALUE)
    val navControllerStateFlow = MutableStateFlow<NavController?>(null)
    private fun NavController.handleNavigationEvent(navEvent: NavigationEvent) {
        when (navEvent) {
            is NavigationEvent.NavigateTo -> {
                navigate(navEvent.route)
            }
        }
    }

    suspend fun subscribe(navController: NavController) {
        navigationEventFlow
            .onSubscription { this@Navigator.navControllerStateFlow.value = navController }
            .onCompletion { this@Navigator.navControllerStateFlow.value = null }
            .collect { navController.handleNavigationEvent(it) }
    }

    fun navigate(navEvent: NavigationEvent) {
        navigationEventFlow.tryEmit(navEvent)
    }
}
