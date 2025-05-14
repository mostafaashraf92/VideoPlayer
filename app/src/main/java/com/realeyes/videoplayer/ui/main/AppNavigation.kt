package com.realeyes.videoplayer.ui.main

import MediaListScreen
import PlayerScreen
import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.realeyes.feature.NavigationViewModel
import com.realeyes.common_ui.navigation.Navigator
import com.realeyes.feature.Screen
import com.realeyes.feature.videos_list.presentation.VideoItemUIModel
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val navigator: Navigator = koinInject()
    val navController: NavHostController = rememberNavController()
    val navigationViewModel: NavigationViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        navigator.subscribe(navController)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.MediaList.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        }
    ) {
        composable(
            route = Screen.MediaList.route
        ) {
            MediaListScreen(navigationViewModel)
        }
        composable(
            route = Screen.PlayerScreen.route,
            arguments = listOf(navArgument(Screen.PlayerScreen.ARG_VIDEO) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val video = backStackEntry.arguments?.getString(Screen.PlayerScreen.ARG_VIDEO)?.let {
                Json.decodeFromString<VideoItemUIModel>(it)
            } ?: VideoItemUIModel(null, null, null, 0L, null)

            PlayerScreen(
                navigationViewModel,
                video
            )
        }
    }
}
