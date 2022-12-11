package com.example.assignment_for_daresay.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.data.model.DetailedReview
import com.example.assignment_for_daresay.data.model.Reviews

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieReviews(movieId : Int, movieViewModel : MovieViewModel) {

    val movieReviews = produceState<Reviews?>(initialValue = null) {
        value = movieViewModel.getMovieReviews(movieId)
    }

    if (movieReviews.value != null) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = movieId.toString()
                )
            }
            items(movieReviews.value!!.results){
                reviewCard(reviews = it)
            }
        }
    }
}

@Composable
fun reviewCard(reviews: DetailedReview){
        Card() {
            Column() {
                Row(){
                    reviews.author_details?.avatar_path?.let {
                        Text(
                            text = it
                        )
                    }
                    reviews.author?.let {
                        Text(
                            text = it,
                        )
                    }
                }

                reviews.created_at?.let {
                    Text(
                        text = it.substring(0,10)
                    )
                }
                reviews.content?.let {
                    Text(
                        text = it
                    )
                }
            }
        }
}