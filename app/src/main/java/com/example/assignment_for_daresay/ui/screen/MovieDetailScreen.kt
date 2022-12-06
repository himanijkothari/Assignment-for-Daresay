package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.data.model.MovieDetailClass

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieDetailScreen() {

     Box(){
         Text(text = "detail screen")
         /*Image(
             painter = rememberAsyncImagePainter(model = IMAGE_URL + movie.poster_path) ,
             contentDescription = null,
             modifier = Modifier.fillMaxSize(),
             contentScale = ContentScale.FillBounds
         )*/
     }
}