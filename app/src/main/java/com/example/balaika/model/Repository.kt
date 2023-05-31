package com.example.balaika.model

import com.example.balaika.model.room.BalaikaDatabase
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song
import kotlinx.coroutines.flow.Flow

class Repository(private val database: BalaikaDatabase, private val dataStore: BalaikaDataStore) {

    val setupValues: Map<BalaikaDataStore.DataType, Flow<Boolean>>
        get() = dataStore.values

    fun getAllSongs(): Flow<List<Song>> = database.songDao().getAll()

    fun insert(song: Song) = song.copy(id = database.songDao().insert(song).toInt())

    fun update(song: Song) = database.songDao().update(song)

    suspend fun getPlaysForSong(songId: Int): List<Play> = database.playDao().getPlaysForSong(songId)

    fun insert(play: Play) = database.playDao().insert(play)

    suspend fun updateSetup(key: BalaikaDataStore.DataType, value: Boolean) = dataStore.update(key, value)
}