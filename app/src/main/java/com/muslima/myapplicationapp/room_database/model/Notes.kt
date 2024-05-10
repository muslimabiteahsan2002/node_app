package com.muslima.myapplicationapp.room_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var title:String="",
    var subtitle:String="",
    var date:String="",
    var notes:String="",
    var priority:String=""
)