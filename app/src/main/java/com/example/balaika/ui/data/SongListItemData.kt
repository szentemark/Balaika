package com.example.balaika.ui.data

val dummySongListItemData = SongListItemData(
    title = "No soy el aire",
    author = "Gaby Moreno",
    lastPlayed = "Played: 2023.04.17",
    averageLength = "Length: 3:45"
)

val dummySongListData = listOf(
    dummySongListItemData,
    dummySongListItemData,
    dummySongListItemData
)

data class SongListItemData(
    val title: String,
    val author: String,
    val lastPlayed: String,
    val averageLength: String
)