package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.entities.Todo

class MainActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        val db = Room.databaseBuilder(applicationContext, TodoDatabase::class.java, "todo-db")
        .allowMainThreadQueries().build()

        val todoDao = db.todoDao()
        var TodoList = todoDao.getAll()


        var adapter = TodoAdapter(todoDao.getAll(), this, todoDao)
        val rvView: RecyclerView = findViewById(R.id.rvView)

        rvView.adapter = adapter
        rvView.layoutManager = LinearLayoutManager(this)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val editText: EditText = findViewById(R.id.editTxt)


        btnAdd.setOnClickListener {
            if (!checkNull(editText)) {
                val title = editText.text.toString()

                val todo = Todo(title)

                todoDao.insert(todo)

                todoDao.getAll().forEach {
                    Log.e("Todo", it.toString())
                }
                adapter = TodoAdapter(todoDao.getAll(), this, todoDao)
                rvView.adapter = adapter

                val inputManager: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                editText.text.clear()
            } else {
                Toast.makeText(this@MainActivity, "Please input the note!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkNull(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }
}