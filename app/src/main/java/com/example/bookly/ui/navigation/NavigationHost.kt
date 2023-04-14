package com.example.bookly.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bookly.ui.authentication.Login
import com.example.bookly.ui.authentication.Registration
import com.example.bookly.ui.launch.Launch
import com.example.bookly.ui.main.BookDetails
import com.example.bookly.ui.main.BookList
import com.example.bookly.ui.settings.Settings
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun NavigationHost(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = NavigationRoutes.Launch.route,
    isLoggedIn: Boolean,
) {
    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(
            route = NavigationRoutes.Launch.route
        ) {
            Launch(
                navigateToNextScreen = {
                    if (isLoggedIn) navController.navigate(NavigationRoutes.BookList.route)
                    else navController.navigate(NavigationRoutes.Login.route)
                }
            )
        }

        composable(
            route = NavigationRoutes.Login.route,
        ) {
            Login(
                navigateToRegistration = { navController.navigate(NavigationRoutes.Registration.route) },
                navigateToMainScreen = { navController.navigate(NavigationRoutes.BookList.route) }
            )
        }

        composable(
            route = NavigationRoutes.Registration.route
        ) {
            Registration(
                navigateToLogin = { navController.popBackStack() }
            )
        }

        composable(
            route = NavigationRoutes.BookList.route
        ) {
            BookList(
                navigateToBookDetails = { navController.navigate("${NavigationRoutes.BookDetails.route}/bookId=${it}") },
                navigateToSetting = { navController.navigate(NavigationRoutes.Settings.route) }
            )
        }

        composable(
            route = "${NavigationRoutes.BookDetails.route}/bookId={bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) {
            val id = requireNotNull(it.arguments).getString("bookId")
            if (id != null) {
                BookDetails(
                    bookId = id,
                    navigateToPreviousScreen = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = NavigationRoutes.Settings.route
        ) {
            Settings(
                navigateToPreviousScreen = { navController.popBackStack() },
                navigateToLogin = { navController.navigate(NavigationRoutes.Login.route) }
            )
        }
    }
}