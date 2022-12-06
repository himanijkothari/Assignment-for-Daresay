package com.example.assignment_for_daresay

import androidx.navigation.NamedNavArgument

sealed class Screens(
    val route:String,
    val arguments:List<NamedNavArgument> = emptyList()
)

object Home: Screens (
    route = "home"
)

object Details: Screens (
    route = "details"
)