package com.example.assignment_for_daresay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_for_daresay.data.controller.MovieController
import com.example.assignment_for_daresay.data.model.Movies
import com.example.assignment_for_daresay.data.model.Details
import com.example.assignment_for_daresay.data.model.Reviews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieController: MovieController
): ViewModel() {

    suspend fun getTopRatedMovies() : Movies? =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            val response = movieController.getTopRatedMovies("en-US",(1..5).random())
            if(response.isSuccessful){
                response.body()
            } else {
                null
            }
        }

    suspend fun getPopularMovies() : Movies? =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            val response = movieController.getPopularMovies("en-US",(1..5).random())
            if(response.isSuccessful){
                response.body()
            } else {
                null
            }
        }

    suspend fun getMovieDetails(id: Int) : Details? =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            val response = movieController.getMovieDetail(id,"en-US" )
            if(response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }

    suspend fun getMovieReviews(id : Int) : Reviews? =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            val response = movieController.getMovieReviews(id,"en-US")
            if(response.isSuccessful){
                response.body()
            } else {
                null
            }
        }
}