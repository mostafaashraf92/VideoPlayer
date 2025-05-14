package com.realeyes.feature

import com.realeyes.common_ui.navigation.Navigator
import com.realeyes.core.viewmodel.BaseViewModel
import com.realeyes.core.viewmodel.ViewSideEffect
import io.dolby.app.navigation.NavigationEvent

class NavigationViewModel(private val navigator: Navigator) :
    BaseViewModel<NavAction, ViewSideEffect>() {
    override fun onUiAction(uiAction: NavAction) {
        val route = when (uiAction) {
            is NavAction.ToMediaList -> Screen.MediaList.route
            is NavAction.ToPlayerScreen -> {
                Screen.PlayerScreen.createRoute(uiAction.videoItem)
            }
        }
        navigator.navigate(NavigationEvent.NavigateTo(route))
    }
}
