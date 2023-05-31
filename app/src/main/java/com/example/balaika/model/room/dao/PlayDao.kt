package com.example.balaika.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.balaika.model.room.entity.Play

@Dao
interface PlayDao {
    @Query("SELECT * FROM play WHERE song_id = :songId ORDER BY from_time DESC LIMIT 20")
    suspend fun getPlaysForSong(songId: Int): List<Play>

    @Insert
    fun insert(play: Play)
}