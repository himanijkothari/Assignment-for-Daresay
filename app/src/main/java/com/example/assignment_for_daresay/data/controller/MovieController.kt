package com.example.assignment_for_daresay.data.controller


import com.example.assignment_for_daresay.data.model.Details
import com.example.assignment_for_daresay.data.model.Movies
import com.example.assignment_for_daresay.data.model.Reviews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieController {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("language") language: String, @Query("page") page: Int) : Response<Movies>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") language: String, @Query("page") page: Int) : Response<Movies>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int,@Query("language") language: String ) : Response<Details>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviews(@Path("movieId") movieId: Int, @Query("language") language: String  ) : Response<Reviews>
}