package com.example.balaika.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.balaika.model.room.entity.Play
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayDao {
    @Query("SELECT * FROM play WHERE song_id = :songId ORDER BY from_time DESC LIMIT 20")
    fun getPlaysForSong(songId: Int): Flow<List<Play>>

    @Insert
    fun insert(play: Play)
}