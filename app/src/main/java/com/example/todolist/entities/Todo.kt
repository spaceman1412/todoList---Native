package com.example.todolist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    var title: String,
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    var isChecked: Boolean = false
)
{


}
