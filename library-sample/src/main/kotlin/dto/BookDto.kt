package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateBookRequest(
    val title: String,
    val author: String
)

@Serializable
data class BookResponse(
    val title: String,
    val author: String,
    val isAvailable: Boolean
)

@Serializable
data class UpdateBookRequest(
    val title: String? = null,
    val author: String? = null,
    val isAvailable: Boolean? = null
)

@Serializable
data class AvailabilityResponse(
    val isAvailable: Boolean
)

@Serializable
data class SearchRequest(
    val filter: String
)

@Serializable
data class SearchResponse(
    val list: List<BookResponse>
)