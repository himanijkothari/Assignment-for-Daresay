package com.example.assignment_for_daresay

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(
    val route:String,
    val arguments:List<NamedNavArgument> = emptyList()
)

object Home: Screens (
    route = "home"
)

object Details: Screens (
    route = "details/{movie}",
    arguments = listOf(
        navArgument("movie") {
            nullable = false
            type = NavType.IntType
        }
    )
)