package com.example.bookly.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bookly.ui.authentication.Login
import com.example.bookly.ui.authentication.Registration
import com.example.bookly.ui.main.BookList
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun NavigationHost(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = NavigationRoutes.Login.route
) {
    AnimatedNavHost(navController = navController, startDestination = startDestination) {

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
            BookList()
        }
    }
}