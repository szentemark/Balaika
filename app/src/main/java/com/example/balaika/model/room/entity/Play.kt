package com.example.balaika.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "play", indices = [Index(value = ["song_id"]), Index(value = ["from_time"])])
data class Play(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "song_id") val songId: Int,
    @ColumnInfo(name = "from_time") val from: ZonedDateTime,
    @ColumnInfo(name = "till_time") val till: ZonedDateTime
)