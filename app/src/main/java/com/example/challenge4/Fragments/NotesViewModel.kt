package com.example.challenge4.Fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.challenge4.datalocal.NotesEntity
import com.example.challenge4.database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val readAllNotes: LiveData<List<NotesEntity>>
    private val repository: NotesRepository

    init {
        val notesDAO = NotesDatabase.getDatabase(application).notesDAO()
        repository = NotesRepository(notesDAO)
        readAllNotes = repository.readAllNotes
    }

    fun insertNotes(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNotes(notes)
        }
    }

    fun updateNotes(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(notes)
        }
    }

    fun deleteNotes(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteNotes(notes)
        }
    }
}