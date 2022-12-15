package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.R
import com.example.assignment_for_daresay.data.model.Movies
import com.example.assignment_for_daresay.ui.theme.appRed
import com.example.assignment_for_daresay.ui.theme.gradientRadial
import com.example.assignment_for_daresay.ui.widget.MovieDetailCard

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel,
    navController: NavController
) {

    val topRatedMovie = produceState<Movies?>(initialValue = null){
        value = movieViewModel.getTopRatedMovies()
    }

    val popularMovie = produceState<Movies?>(initialValue = null){
        value = movieViewModel.getPopularMovies()
    }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            text = { Text(text = stringResource(id = R.string.message_search_dialogue)) },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text(text = stringResource(id = R.string.label_ok))
                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .background(gradientRadial)
            .fillMaxSize()
            .padding(16.dp)
    ){
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(bottom = 32.dp)
            ){
                Divider(
                    color = appRed,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dp)
                )
                Text(
                    text = stringResource(id = R.string.label_welcome),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.White,
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.width(200.dp))

                Icon(
                    Icons.Filled.Search,
                    null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                           showDialog = true
                        },
                    tint = Color.White
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(start = 4.dp)
            ){
                Divider(
                    color = appRed,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dp)
                )
                Text(
                    text = stringResource(id = R.string.label_top_rated_movies),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h2
                )
            }
        }

        item {
            if(topRatedMovie.value!= null){
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(topRatedMovie.value!!.results) {
                        MovieDetailCard(it , navController)
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
            ) {
                Divider(
                    color = appRed,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(4.dp)
                )
                Text(
                    text = stringResource(id = R.string.label_popular_movies),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h2
                )
            }
        }

        item {
            if(popularMovie.value!= null){
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(popularMovie.value!!.results) {
                        MovieDetailCard(it, navController)
                    }
                }
            }
        }
    }
}