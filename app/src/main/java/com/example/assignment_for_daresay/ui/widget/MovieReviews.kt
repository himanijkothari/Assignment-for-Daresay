package com.example.assignment_for_daresay.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment_for_daresay.MovieViewModel
import com.example.assignment_for_daresay.R
import com.example.assignment_for_daresay.data.model.DetailedReview
import com.example.assignment_for_daresay.data.model.Reviews
import com.example.assignment_for_daresay.ui.theme.appRed
import com.example.assignment_for_daresay.ui.theme.golden
import com.example.assignment_for_daresay.ui.theme.gradientRadial
import me.nikhilchaudhari.library.NeuInsets
import me.nikhilchaudhari.library.neumorphic
import me.nikhilchaudhari.library.shapes.Pressed
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun MovieReviews(movieId : Int, movieViewModel : MovieViewModel) {

    val movieReviews = produceState<Reviews?>(initialValue = null) {
        value = movieViewModel.getMovieReviews(movieId)
    }

    val isReviewAvailable = movieReviews.value?.results?.size

    if(isReviewAvailable != null){
        if(isReviewAvailable != 0){
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(movieReviews.value!!.results){
                    reviewCard(reviews = it)
                }
            }
        } else {
            Text(
                text = stringResource(id = R.string.label_no_reviews),
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Composable
fun reviewCard(reviews: DetailedReview){

    val localDateTime = LocalDate.parse(reviews.created_at?.substring(0,10))
    val formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")
    val parsedDate = formatter.format(localDateTime)

    Card(
        modifier = Modifier
            .padding(16.dp)
            .neumorphic(
                neuShape = Pressed.Rounded(16.dp),
                lightShadowColor = Color.White,
                darkShadowColor = Color(0xFFEDF1F4),
                strokeWidth = 2.dp,
                neuInsets = NeuInsets(3.dp, 3.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                if(reviews.author_details?.avatar_path != null){
                    AsyncImage(
                        model = IMAGE_URL + reviews.author_details.avatar_path,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(1.dp, appRed, CircleShape)
                    )
                } else {
                    Text(
                        text = reviews.author?.first().toString().uppercase(),
                        modifier = Modifier
                            .padding(16.dp)
                            .drawBehind {
                                drawCircle(
                                    appRed,
                                    radius = this.size.maxDimension
                                )
                            }
                            .background(appRed),
                        color = Color.White

                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column() {
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp)
                    ) {
                        reviews.author?.let {
                            Text(
                                text = " A review by $it",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        if(reviews.author_details?.rating != null) {
                            Row(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(appRed)
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    modifier = Modifier.padding(end = 4.dp),
                                    text = reviews.author_details?.rating.toString(),
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                        }

                    }

                    Text(
                        text = "written on $parsedDate",
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            reviews.content?.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}