package com.example.balaika.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.balaika.model.room.dao.PlayDao
import com.example.balaika.model.room.dao.SongDao
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song

@Database(entities = [Song::class, Play::class], version = 1)
@TypeConverters(value = [Converters::class])
abstract class BalaikaDatabase: RoomDatabase() {

    abstract fun songDao(): SongDao
    abstract fun playDao(): PlayDao
}