package com.muslima.myapplicationapp.room_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.muslima.myapplicationapp.room_database.model.Notes

@Dao
interface NotesDao {
    @Query("select * from notes_table")
    fun showAllNotes(): LiveData<List<Notes>>
    @Query("select * from notes_table order by priority desc")
    fun highToLoew(): LiveData<List<Notes>>
    @Query("select * from notes_table order by priority asc")
    fun lowToHigh(): LiveData<List<Notes>>

    @Insert
    fun insertNotes(notes: Notes)

    @Query("delete from notes_table where id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}