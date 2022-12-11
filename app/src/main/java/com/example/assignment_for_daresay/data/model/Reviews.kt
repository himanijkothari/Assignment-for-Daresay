package com.example.assignment_for_daresay.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reviews (
    val id : Int,
    val page : Int,
    val results : List<DetailedReview>,
    val total_pages : Int,
    val total_results : Int
)

@JsonClass(generateAdapter = true)
data class DetailedReview (
    val author : String?,
    val author_details : AuthorDetails?,
    val content : String?,
    val created_at : String?,
    val id : String?,
    val updated_at : String?,
    val url : String?,
)

@JsonClass(generateAdapter = true)
data class AuthorDetails (
    val name: String?,
    val username : String?,
    val avatar_path : String? = null,
    val rating : Int? = null
)