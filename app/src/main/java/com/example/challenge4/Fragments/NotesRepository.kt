package com.example.challenge4.Fragments

import androidx.lifecycle.LiveData
import com.example.challenge4.datalocal.NotesDAO
import com.example.challenge4.datalocal.NotesEntity

class NotesRepository(private val notesDAO: NotesDAO) {
    val readAllNotes: LiveData<List<NotesEntity>> = notesDAO.readAllNotes()

    suspend fun addNotes(notes: NotesEntity){
        notesDAO.addNotes(notes)
    }

    suspend fun updateNotes(notes: NotesEntity){
        notesDAO.updateNotes(notes)
    }

    suspend fun deleteNotes(notes: NotesEntity){
        notesDAO.deleteNotes(notes)
    }

}