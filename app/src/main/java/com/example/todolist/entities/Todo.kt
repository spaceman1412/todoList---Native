package com.example.todolist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @PrimaryKey
    val title: String,
    var isChecked: Boolean = false ,
)
