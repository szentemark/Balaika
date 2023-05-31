package com.example.balaika.model.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.balaika.model.room.dao.PlayDao
import com.example.balaika.model.room.dao.SongDao
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song

@Database(
    entities = [Song::class, Play::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
@TypeConverters(value = [Converters::class])
abstract class BalaikaDatabase: RoomDatabase() {

    abstract fun songDao(): SongDao
    abstract fun playDao(): PlayDao

    companion object {
        @Volatile
        private var INSTANCE: BalaikaDatabase? = null

        fun getDatabase(context: Context): BalaikaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    BalaikaDatabase::class.java,
                    "balaika_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}