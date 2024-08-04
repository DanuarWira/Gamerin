package com.example.gamerin.model

data class Game(
    val id: Int,
    val image: Int,
    val title: String,
    val description: String,
    val price: Int,
    val releaseDate: String,
    val developer: String,
    val publisher: String,
    val genre: List<String>
)