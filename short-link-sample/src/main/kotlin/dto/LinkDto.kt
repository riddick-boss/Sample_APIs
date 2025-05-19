package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShortenRequest(
    val link: String
)

@Serializable
data class ShortenResponse(
    val shortLink: String
)

@Serializable
data class GetOriginalRequest(
    val shortLink: String
)

@Serializable
data class GetOriginalResponse(
    val originalLink: String
)