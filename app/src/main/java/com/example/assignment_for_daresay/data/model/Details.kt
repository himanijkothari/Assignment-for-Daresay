package com.example.assignment_for_daresay.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Details (
    val adult : Boolean,
    val backdrop_path: String? = null,
    val genres: List<Genre>,
    val homepage : String? = null,
    val id: Int,
    val imdb_id : String? = null,
    val original_language: String?,
    val original_title: String?,
    val overview: String? = null,
    val popularity: Double?,
    val poster_path: String? = null,
    val production_companies: List<Production_companies>?,
    val production_countries: List<Production_countries>?,
    val release_date: String?,
    val revenue : Long?,
    val runtime: Int? = null,
    val spoken_languages: List<Spoken_languages>?,
    val status: String,
    val tagline: String? = null,
    val title: String?,
    val vote_count: Int?,
    val video: Boolean?,
    val vote_average: Double?
)

 @JsonClass(generateAdapter = true)
data class Genre (
    val id: Int?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class Production_companies (
    val name: String?,
    val id: Int?,
    val logo_path : String?= null,
    val origin_country : String?
)

@JsonClass(generateAdapter = true)
data class Production_countries (
    val iso_3166_1 : String?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class Spoken_languages(
    val iso_639_1 : String?,
    val name: String?
)