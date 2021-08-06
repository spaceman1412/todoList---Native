package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        var TodoList = mutableListOf(
            Todo("Hello"),
            Todo("Hello"),
            Todo("Hello"),
        )

        val adapter = TodoAdapter(TodoList)
        val rvView : RecyclerView = findViewById(R.id.rvView)

        rvView.adapter = adapter
        rvView.layoutManager = LinearLayoutManager(this)

        val btnAdd : Button = findViewById(R.id.btnAdd)
        val editText : EditText = findViewById(R.id.editTxt)

        btnAdd.setOnClickListener{
            val title = editText.text.toString()
            val todo = Todo(title)
            TodoList.add(todo)
            adapter.notifyItemInserted(TodoList.size -1)
        }

    }
}