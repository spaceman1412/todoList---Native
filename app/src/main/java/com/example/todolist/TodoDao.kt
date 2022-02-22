package com.example.todolist

import androidx.room.*
import com.example.todolist.entities.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAll(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getTodoWithId(id : Int) : Todo

    @Query("DELETE FROM Todo")
    fun deleteAll()

    @Update
    fun update(todo: Todo)


}