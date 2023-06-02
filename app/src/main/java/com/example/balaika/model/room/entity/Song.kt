package com.example.balaika.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.ZonedDateTime

@Entity(tableName = "song")
data class Song(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "image_file") val imageFile: String,
    @ColumnInfo(name = "scrumming") val scrumming: Int,
    @ColumnInfo(name = "pick") val pick: Boolean,
    @ColumnInfo(name = "left_hand_heavy") val leftHandHeavy: Boolean,
    @ColumnInfo(name = "feature_song") val featureSong: Boolean,
    @ColumnInfo(name = "show_in_playroom") val showInPlayroom: Boolean,
    @ColumnInfo(name = "last_played") val lastPlayed: ZonedDateTime?,
    @ColumnInfo(name = "average_length") val averageLength: Duration?,
    @ColumnInfo(name = "play_count", defaultValue = "0") val playCount: Int
)