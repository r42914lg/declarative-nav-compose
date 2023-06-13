package com.r42914lg.declarativenavcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = screenARoute,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        screenANavGraphBuilder(
            navController::navigateToB,
            navController::popBackStack,
        )
        screenBNavGraphBuilder(
            navController::navigateToC,
            navController::popBackStack,
        )
        screenCNavGraphBuilder(
            navController::navigateToA,
            navController::popBackStack,
        )
    }
}

/**
 * Screen A navigation
 */

const val screenARoute = "screen_A"

fun NavGraphBuilder.screenANavGraphBuilder(onClick: () -> Unit, onBack: () -> Unit) {
    composable(screenARoute) {
        ScreenA(
            onClick = onClick,
            onClickBack = onBack
        )
    }
}

fun NavController.navigateToB() {
    navigate(screenBRoute)
}

/**
 * Screen B navigation
 */

const val screenBRoute = "screen_B"

fun NavGraphBuilder.screenBNavGraphBuilder(onClick: () -> Unit, onBack: () -> Unit) {
    composable(screenBRoute) {
        ScreenB(
            onClick = onClick,
            onClickBack = onBack,
        )
    }
}

fun NavController.navigateToA() {
    navigate(screenARoute)
}

/**
 * Screen C navigation
 */

const val screenCRoute = "screen_C"

fun NavGraphBuilder.screenCNavGraphBuilder(onClick: () -> Unit, onBack: () -> Unit) {
    composable(screenCRoute) {
        ScreenC(
            onClick = onClick,
            onClickBack = onBack,
        )
    }
}

fun NavController.navigateToC() {
    navigate(screenCRoute)
}