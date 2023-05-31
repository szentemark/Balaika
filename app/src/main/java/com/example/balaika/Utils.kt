package com.example.balaika

import com.example.balaika.model.room.entity.Song
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

const val COVER_IMAGES_DIRECTORY = "cover_images"

fun Song.calculateImageFilePath(timestamp: Long, extension: String): String = "$COVER_IMAGES_DIRECTORY/song_${id}_$timestamp$extension"

fun Duration.mmSs(): String {
    val seconds = toMillis() / 1000 % 60
    val minutes = toMillis() / 1000 / 60
    return "$minutes:${seconds.toString().padStart(2, '0')}"
}

fun ZonedDateTime.yyyyMmDd(): String =
    DateTimeFormatter
        .ofPattern("uuuu.MM.dd")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())
        .format(this)
