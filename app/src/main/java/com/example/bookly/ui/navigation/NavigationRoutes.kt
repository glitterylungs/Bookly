package com.example.bookly.ui.navigation

internal sealed class NavigationRoutes(val route: String) {
    object Launch : NavigationRoutes("launch")
    object Login : NavigationRoutes("login")
    object Registration : NavigationRoutes("registration")
    object BookList : NavigationRoutes("bookList")
}