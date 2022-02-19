package com.example.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.entities.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAll(): List<Todo>

    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)


}