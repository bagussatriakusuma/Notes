package com.example.challenge4.datalocal

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun readAllNotes(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(notes: NotesEntity)

    @Update
    suspend fun updateNotes(notes: NotesEntity)

    @Delete
    suspend fun deleteNotes(notes: NotesEntity)
}