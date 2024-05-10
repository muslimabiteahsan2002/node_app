package com.muslima.myapplicationapp.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muslima.myapplicationapp.room_database.dao.NotesDao
import com.muslima.myapplicationapp.room_database.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}