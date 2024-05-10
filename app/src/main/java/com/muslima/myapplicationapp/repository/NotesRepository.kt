package com.muslima.myapplicationapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.muslima.myapplicationapp.room_database.RoomClint
import com.muslima.myapplicationapp.room_database.model.Notes

class NotesRepository(application: Application) {
    private val database = RoomClint.getInstance(application)
    private val notesDao = database.notesDao()
    var showAllNotes: LiveData<List<Notes>> = notesDao.showAllNotes()
    var highToLow: LiveData<List<Notes>> = notesDao.highToLoew()
    var lowToHigh: LiveData<List<Notes>> = notesDao.lowToHigh()
    fun insertNotes(notes: Notes){
        notesDao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        notesDao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        notesDao.updateNotes(notes)
    }
}