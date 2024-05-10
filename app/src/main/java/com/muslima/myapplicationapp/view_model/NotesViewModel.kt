package com.muslima.myapplicationapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.muslima.myapplicationapp.repository.NotesRepository
import com.muslima.myapplicationapp.room_database.model.Notes

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private var repository: NotesRepository = NotesRepository(application)
    var showAllNotes: LiveData<List<Notes>> = repository.showAllNotes
    var highToLow: LiveData<List<Notes>> = repository.highToLow
    var lowToHigh: LiveData<List<Notes>> = repository.lowToHigh
    fun insertNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}