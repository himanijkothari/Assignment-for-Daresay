package com.example.assignment_for_daresay

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment_for_daresay.ui.MovieNavGraph
import com.example.assignment_for_daresay.ui.theme.appBarRadial
import com.example.assignment_for_daresay.ui.theme.gradientRadial
import com.example.assignment_for_daresay.ui.theme.neuUp
import com.example.assignment_for_daresay.ui.theme.neuWhite
import me.nikhilchaudhari.library.neumorphic
import me.nikhilchaudhari.library.shapes.Pressed
import me.nikhilchaudhari.library.shapes.Punched


@Composable
fun MovieApp(
    movieViewModel: MovieViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomTabRoutes = listOf(Home,Download, Profile)
    Scaffold(
        bottomBar = { BottomAppBar(
            modifier = Modifier
               // .padding(vertical = 4.dp, horizontal = 24.dp)
                .height(88.dp)
                .clip(RoundedCornerShape(16.dp)),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
        ){
            bottomTabRoutes.forEach { screen ->
                NavButton(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    screens = screen,
                    selected = screen.route == currentRoute
                ) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        } }
    ) {
        MovieNavGraph(navController, movieViewModel)
    }
}

@Composable
fun NavButton(
    modifier: Modifier = Modifier,
    screens: Screens,
    selected : Boolean = false,
    onClick: () -> Unit = {}
){
    val neuShape = if (selected) Pressed.Rounded(12.dp) else Punched.Rounded(12.dp)
    val cardModifier = modifier
        .padding(8.dp)
        .size(82.dp)
        .neumorphic(
            neuShape = neuShape,
            elevation = 1.dp,
            strokeWidth = if (selected) 0.dp else 6.dp,
            lightShadowColor = if (selected) Color.LightGray else neuWhite,
            darkShadowColor = if (selected) Color.DarkGray else neuUp
        )
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick
        )

    Card(
        modifier = if (selected) cardModifier.clip(RoundedCornerShape(12.dp)) else cardModifier,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = neuWhite,
    ) {
        val bgModifier = if (selected) Modifier.background(gradientRadial) else Modifier.background(neuWhite)
        Box(
            modifier = bgModifier
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val color = if (selected) neuWhite else Color.DarkGray
                Icon(
                    screens.icon,
                    null,
                    tint = color,
                    modifier = Modifier.size(24.dp, 24.dp)
                )
                Text(
                    text = stringResource(id = screens.resourceId),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button.copy(fontSize = 12.sp, color = color)
                )
            }
        }
    }
}


