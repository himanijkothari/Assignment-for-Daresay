package com.example.assignment_for_daresay

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(
    val route:String,
    val arguments:List<NamedNavArgument> = emptyList(),
    val icon: ImageVector = Icons.Default.Info,
    @StringRes val resourceId: Int
)

object Home: Screens (
    route = "home",
    icon = Icons.Filled.Home,
    resourceId = R.string.screen_home
)

object Details: Screens (
    route = "details/{movie}",
    arguments = listOf(
        navArgument("movie") {
            nullable = false
            type = NavType.IntType
        }
    ),
    resourceId = R.string.screen_details
)


object Profile: Screens(
    route = "profile",
    icon = Icons.Filled.AccountCircle,
    resourceId = R.string.screen_profile
)

object Download: Screens(
    route = "downloads",
    icon = Icons.Filled.Favorite,
    resourceId = R.string.screen_download
)