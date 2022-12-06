package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.R
import com.example.assignment_for_daresay.data.model.Movies
import com.example.assignment_for_daresay.ui.widget.MovieDetailCard

@Composable
fun HomeScreen(movieViewModel: MovieViewModel) {

    val topRatedMovie = produceState<Movies?>(initialValue = null){
        value = movieViewModel.getTopRatedMovies()
    }

    val popularMovie = produceState<Movies?>(initialValue = null){
        value = movieViewModel.getPopularMovies()
    }

    LazyColumn(){

        item {
            Text(
                text = stringResource(id = R.string.label_top_rated_movies),
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2
            )
        }

        item {
            if(topRatedMovie.value!= null){
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(topRatedMovie.value!!.results) {
                        MovieDetailCard(it)
                    }
                }
            }
        }

        item {
            Text(
                text = stringResource(id = R.string.label_popular_movies),
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2
            )
        }

        item {
            if(popularMovie.value!= null){
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(popularMovie.value!!.results) {
                        MovieDetailCard(it)
                    }
                }
            }
        }




    }
}