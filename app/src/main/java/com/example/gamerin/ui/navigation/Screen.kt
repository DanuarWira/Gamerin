package com.example.gamerin.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object GameDetail: Screen("home/{gameId}") {
        fun createRoute(gameId: Int) = "home/$gameId"
    }
}