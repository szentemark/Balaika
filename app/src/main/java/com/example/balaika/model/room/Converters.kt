package com.example.balaika.model.room

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class Converters {
    @TypeConverter
    fun zonedDateTimeToLong(zonedDateTime: ZonedDateTime?) = zonedDateTime?.toInstant()?.toEpochMilli()

    @TypeConverter
    fun longToZonedDateTime(long: Long?): ZonedDateTime? = if (long != null) Instant.ofEpochMilli(long).atZone(
        ZoneId.systemDefault()) else null
}