package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.data.model.Details
import com.example.assignment_for_daresay.R
import com.example.assignment_for_daresay.ui.widget.MovieReviews

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieDetailScreen(
    movieId : Int,
    movieViewModel: MovieViewModel,
    navController: NavController
) {

    val movieDetail = produceState<Details?>(initialValue = null){
        value = movieViewModel.getMovieDetails(movieId)
    }

    val runtimeHour = movieDetail.value?.runtime?.div(60)
    val runtimeMinute = movieDetail.value?.runtime?.rem(60)
    val languages = movieDetail.value?.spoken_languages
    val genres = movieDetail.value?.genres
    var selectedTab by rememberSaveable {
        mutableStateOf(DetailScreenTab.Info)
    }
    val selectedColors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White)
    val unselectedColors = ButtonDefaults.buttonColors( backgroundColor = Color.Black,contentColor = Color.Gray)

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(300.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.popBackStack() },
                    tint = Color.Black
                )
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
            Row() {
                Image(
                    painter = rememberAsyncImagePainter(IMAGE_URL + (movieDetail.value?.poster_path)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp, 150.dp),
                    contentScale = ContentScale.FillBounds
                )
                Column() {
                    movieDetail.value?.title?.let {
                        Text(
                            text = it,
                            color = Color.Black
                        )
                    }
                    Row(){
                        Row() {
                            Icon(
                                Icons.Rounded.DateRange,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            movieDetail.value?.release_date?.let {
                                Text(
                                    text = it.substring(0,4),
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        }
                        Text(
                            text = "|"
                        )

                        Row(){
                            Icon(
                               painter = painterResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz48),
                               contentDescription = null,
                                modifier = Modifier.size(20.dp),
                               tint = Color.Black
                            )
                            Text(
                                text = "${runtimeHour}h ${runtimeMinute}m"
                            )
                        }
                        
                        Text(
                            text = "|"
                        )
                        
                        Row(){
                            Icon(
                                painter = painterResource(id = R.drawable.language_fill0_wght400_grad0_opsz48),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color.Black
                            )
                            if (languages != null) {
                                for(language in languages){
                                    language.name?.let {
                                        Text(
                                            text = it,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = if (selectedTab == DetailScreenTab.Info) selectedColors else unselectedColors,
                    onClick = { selectedTab = DetailScreenTab.Info }
                ) {
                    Text(
                        text = stringResource(id = R.string.label_info),
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Button(
                    modifier = Modifier.weight(1f),
                    colors = if (selectedTab == DetailScreenTab.Review) selectedColors else unselectedColors,
                    onClick = { selectedTab = DetailScreenTab.Review }
                ) {
                    Text(
                        text = stringResource(id = R.string.label_reviews),
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }

            when(selectedTab) {
                DetailScreenTab.Info -> {
                    movieDetail.value?.overview?.let {
                        Column(){
                            Text(
                                text = it
                            )
                            if (genres != null) {
                                for(genre in genres){
                                    genre.name?.let { it1 ->
                                        Text(
                                            text = it1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                DetailScreenTab.Review -> {
                    MovieReviews(movieId = movieId , movieViewModel)
                }
            }
        }
    }
}


enum class DetailScreenTab {
    Info, Review
}