package com.example.assignment_for_daresay.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.data.model.MovieDetailClass
import com.example.assignment_for_daresay.ui.theme.golden

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieDetailCard(movie : MovieDetailClass) {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        
        Box(
            modifier = Modifier.size(150.dp,150.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = IMAGE_URL + movie.poster_path ) ,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
                )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp, 14.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(25)),
                horizontalArrangement = Arrangement.Center
            ) {
               Icon(
                   Icons.Default.Star,
                   contentDescription = null,
                   tint = golden
               )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = movie.vote_average.toString(),
                    fontSize = 12.sp
                )

            }
        }

        Spacer(modifier = Modifier.height(2.dp))
        movie.original_title?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                style = MaterialTheme.typography.h2
            )
        }
        Row() {
            movie.release_date?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }


        
    }
}