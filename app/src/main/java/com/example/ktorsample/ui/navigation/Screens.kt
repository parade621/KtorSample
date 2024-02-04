package com.example.ktorsample.ui.navigation

sealed class Screens(val route: String) {
    sealed class NoArgumentsScreen(route: String) : Screens(route) {
        operator fun invoke(): String = route
    }

    object MainScreen : NoArgumentsScreen("Main")

}