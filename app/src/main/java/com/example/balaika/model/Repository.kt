package com.example.balaika.model

import com.example.balaika.model.room.BalaikaDatabase
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song
import kotlinx.coroutines.flow.Flow

class Repository(private val database: BalaikaDatabase) {

    fun getAllSongs(): Flow<List<Song>> = database.songDao().getAll()

    fun insert(song: Song) = song.copy(id = database.songDao().insert(song).toInt())

    fun update(song: Song) = database.songDao().update(song)

    fun getPlaysForSong(songId: Int): Flow<List<Play>> = database.playDao().getPlaysForSong(songId)

    fun insert(play: Play) = database.playDao().insert(play)
}