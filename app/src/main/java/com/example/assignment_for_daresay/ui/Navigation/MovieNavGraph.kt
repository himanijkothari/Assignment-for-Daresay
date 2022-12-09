package com.example.assignment_for_daresay.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment_for_daresay.Details
import com.example.assignment_for_daresay.Home
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.data.model.MovieDetailClass
import com.example.assignment_for_daresay.ui.screen.HomeScreen
import com.example.assignment_for_daresay.ui.screen.MovieDetailScreen
import com.squareup.moshi.Moshi

@Composable
fun MovieNavGraph(
    navController: NavHostController,
    movieViewModel: MovieViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(
            Home.route,
            arguments = Home.arguments
        ) {
            HomeScreen(
                movieViewModel = movieViewModel,
                navController = navController
            )
        }

        composable(
            Details.route,
            arguments = Details.arguments
        ){
            val movieId = it.arguments?.getInt("movie")
            movieId?.let { id->
                MovieDetailScreen(
                    movieId = id,
                    movieViewModel = movieViewModel,
                    navController = navController
                )
            }
        }
    }
}