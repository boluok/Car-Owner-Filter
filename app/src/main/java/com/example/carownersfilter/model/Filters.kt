package com.example.carownersfilter.model

import java.io.Serializable

data class Filters(
    val avatar: String,
    val colors: List<String>,
    val countries: List<String>,
    val createdAt: String,
    val fullName: String,
    val gender: String,
    val id: String
):Serializable