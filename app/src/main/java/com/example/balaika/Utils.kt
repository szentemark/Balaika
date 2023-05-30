package com.example.balaika

import com.example.balaika.model.room.entity.Song

const val COVER_IMAGES_DIRECTORY = "cover_images"

fun Song.calculateImageFilePath(timestamp: Long, extension: String): String = "$COVER_IMAGES_DIRECTORY/song_${id}_$timestamp$extension"