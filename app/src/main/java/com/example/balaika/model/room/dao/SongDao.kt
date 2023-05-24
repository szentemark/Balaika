package com.example.balaika.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.balaika.model.room.entity.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM song ORDER BY title")
    fun getAll(): Flow<List<Song>>

    @Insert
    fun insert(song: Song)

    @Update
    fun update(song: Song)
}