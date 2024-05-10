package com.muslima.myapplicationapp.room_database

import android.content.Context
import androidx.room.Room

object RoomClint {
    private var INSTANCE: NotesDatabase? = null
    fun getInstance(context: Context): NotesDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context,
                NotesDatabase::class.java,
                "notes_database"
            ).allowMainThreadQueries().build()
        }
        return INSTANCE as NotesDatabase
    }
}