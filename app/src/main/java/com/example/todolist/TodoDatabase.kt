package com.example.todolist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.entities.Todo

@Database(
    entities = [
        Todo::class
    ],
    version = 1
)



abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

}