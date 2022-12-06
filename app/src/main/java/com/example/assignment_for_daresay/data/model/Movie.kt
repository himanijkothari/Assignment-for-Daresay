package com.example.assignment_for_daresay.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies (
    val page: Int,
    val results: List<MovieDetailClass>,
    val total_results: Int,
    val total_pages: Int
)

@JsonClass(generateAdapter = true)
data class MovieDetailClass (
    val poster_path: String? = null,
    val adult: Boolean?,
    val overview: String?,
    val release_date: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_title: String?,
    val original_language: String?,
    val title: String?,
    val backdrop_path: String? = null,
    val popularity: Double?,
    val vote_count: Int?,
    val video: Boolean?,
    val vote_average: Double?
)