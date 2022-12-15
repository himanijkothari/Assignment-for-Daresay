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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.data.model.MovieDetailClass
import com.example.assignment_for_daresay.ui.theme.golden
import me.nikhilchaudhari.library.NeuInsets
import me.nikhilchaudhari.library.neumorphic
import me.nikhilchaudhari.library.shapes.Pressed


private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieDetailCard(movie : MovieDetailClass , navController: NavController) {

    val id = movie.id

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .neumorphic(
                neuShape = Pressed.Rounded(16.dp),
                lightShadowColor = Color.White,
                darkShadowColor = Color(0xFFEDF1F4),
                strokeWidth = 2.dp,
                neuInsets = NeuInsets(3.dp, 3.dp)
            )
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(150.dp,150.dp).clickable { navController.navigate("details/${id}") },
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
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp,
                style = MaterialTheme.typography.h2
            )
        }
    }
}