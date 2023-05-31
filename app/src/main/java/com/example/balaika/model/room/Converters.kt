package com.example.balaika.model.room

import androidx.room.TypeConverter
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class Converters {
    @TypeConverter
    fun zonedDateTimeToLong(zonedDateTime: ZonedDateTime?) = zonedDateTime?.toInstant()?.toEpochMilli()

    @TypeConverter
    fun longToZonedDateTime(long: Long?): ZonedDateTime? = if (long != null) Instant.ofEpochMilli(long).atZone(
        ZoneId.systemDefault()) else null

    @TypeConverter
    fun durationToLong(duration: Duration?) = duration?.toMillis()

    @TypeConverter
    fun longToDuration(long: Long?): Duration? = if (long != null) Duration.ofMillis(long) else null
}