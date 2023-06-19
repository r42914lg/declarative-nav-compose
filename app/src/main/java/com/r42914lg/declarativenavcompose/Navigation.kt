package com.r42914lg.declarativenavcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
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

fun NavGraphBuilder.screenANavGraphBuilder(onClick: (Int, String) -> Unit, onBack: () -> Unit) {
    composable(screenARoute) {
        ScreenA(
            onClick = onClick,
            onClickBack = onBack
        )
    }
}

fun NavController.navigateToA() {
    navigate(screenARoute)
}

/**
 * Screen B navigation
 */

const val screenBRoute = "screen_B"
const val someMandatoryArg = "arg_1"
const val someOptionalArg = "arg_2"

fun NavGraphBuilder.screenBNavGraphBuilder(onClick: () -> Unit, onBack: () -> Unit) {
    composable(
        route = "$screenBRoute/{$someMandatoryArg}?$someOptionalArg={$someOptionalArg}",
        arguments = listOf(
            navArgument(someMandatoryArg) { type = NavType.IntType },
            navArgument(someOptionalArg) {
                type = NavType.StringType
                nullable = false
                defaultValue = "some default value"
            }
        )
    ) {
        val mandatoryArg = it.arguments?.getInt(someMandatoryArg) ?: throw IllegalStateException()
        val optionalArg = it.arguments?.getString(someOptionalArg).orEmpty()
        ScreenB(
            onClick = onClick,
            onClickBack = onBack,
            arg1 = mandatoryArg,
            arg2 = optionalArg,
        )
    }
}

fun NavController.navigateToB(mandatoryArgVal: Int, optionalArgVal: String) {
    navigate("$screenBRoute/$mandatoryArgVal?$someOptionalArg=${optionalArgVal}")
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