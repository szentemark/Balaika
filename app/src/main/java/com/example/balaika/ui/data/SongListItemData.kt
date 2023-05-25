package com.example.balaika.ui.data

import com.example.balaika.model.room.entity.Song

data class SongListItemData(
    val song: Song,
    val title: String,
    val author: String,
    val lastPlayed: String,
    val averageLength: String
)