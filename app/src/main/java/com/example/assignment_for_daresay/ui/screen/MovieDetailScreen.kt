package com.example.assignment_for_daresay.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.data.model.Details
import com.example.assignment_for_daresay.R
import com.example.assignment_for_daresay.ui.theme.appRed
import com.example.assignment_for_daresay.ui.theme.lightRed
import com.example.assignment_for_daresay.ui.widget.MovieReviews
import kotlin.text.Typography.bullet

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
    var selectedTab by rememberSaveable {
        mutableStateOf(DetailScreenTab.Info)
    }
    val selectedColors = ButtonDefaults.buttonColors(backgroundColor = lightRed, contentColor = Color.White)
    val unselectedColors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.LightGray)


            Column(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ){
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(model = IMAGE_URL + movieDetail.value?.poster_path),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .aspectRatio(1.25f),
                        contentScale = ContentScale.FillBounds,
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    0F to Color.Transparent,
                                    .5F to Color.Black.copy(alpha = 0.5F),
                                    1F to Color.Black.copy(alpha = 0.8F)
                                )
                            )
                            .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
                    ) {
                        movieDetail.value?.title?.let {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 32.dp),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = it,
                                    fontSize = 32.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h1
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Rounded.DateRange,
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            movieDetail.value?.release_date?.let {
                                Text(
                                    text = it.substring(0,4),
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Divider(
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz48),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${runtimeHour}h ${runtimeMinute}m",
                                fontSize = 12.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle1
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Divider(
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.language_fill0_wght400_grad0_opsz48),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            if (languages != null) {
                                val last = languages.last().name
                                for(language in languages){
                                    language.name?.let {
                                        if(it == last)Text(
                                            text = it,
                                            fontSize = 12.sp,
                                            color = Color.White,
                                            style = MaterialTheme.typography.subtitle1
                                        ) else {
                                            Text(
                                                text = "$it, ",
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
                       // border = if (selectedTab == DetailScreenTab.Info) BorderStroke(2.dp, appRed) else  BorderStroke(0.dp, Color.Transparent),
                        onClick = { selectedTab = DetailScreenTab.Info }
                    ){
                        Text(
                            text = stringResource(id = R.string.label_info),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                       // border = if (selectedTab == DetailScreenTab.Review) BorderStroke(2.dp, appRed) else  BorderStroke(0.dp, Color.Transparent),
                        colors = if (selectedTab == DetailScreenTab.Review) selectedColors else unselectedColors,
                        onClick = { selectedTab = DetailScreenTab.Review }
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_reviews),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
                when(selectedTab) {
                    DetailScreenTab.Info -> {
                            LazyColumn(
                                contentPadding = PaddingValues(8.dp),
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ){
                                item {
                                    Text(
                                        text = stringResource(id = R.string.label_story_line),
                                        color = appRed,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                }

                                item{
                                    movieDetail.value?.overview?.let {
                                        Text(
                                            text = it,
                                            color = Color.White
                                        )
                                    }
                                }
                                item{
                                    Row() {

                                        movieDetail.value?.genres?.forEach {
                                            val last = movieDetail.value?.genres?.last()?.name
                                            it.name?.let { it1 ->
                                                if(it1 == last) {
                                                    Text(
                                                        text = " $it1",
                                                        color = Color.LightGray
                                                    )
                                                } else {
                                                    Text(
                                                        text = " $it1  $bullet ",
                                                        color = Color.LightGray
                                                    )
                                                }
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




enum class DetailScreenTab {
    Info, Review
}