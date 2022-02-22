package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.example.todolist.entities.Todo
import com.google.android.material.textfield.TextInputEditText

class ItemEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_edit)

        val extras : Bundle? = intent.extras

        var key : Int = 0
        if(extras != null )
        {
            key  = extras.getInt("KEY",0)
        }

        val db = Room.databaseBuilder(applicationContext,TodoDatabase::class.java,"todo-db").allowMainThreadQueries().build()

        val todoDao = db.todoDao()

        val toDo : Todo? = todoDao.getTodoWithId(key)

        val textField : TextInputEditText = findViewById(R.id.textField)

        textField.setText(toDo?.title)

        val finishBtn : TextView = findViewById(R.id.txtView_btn_finish)
        val cancelBtn : TextView = findViewById(R.id.txtView_btn_cancel)

        val intent : Intent = Intent(this,MainActivity::class.java)
        finishBtn.setOnClickListener {
            val newText : String = textField.text.toString()
            val todo : Todo = Todo(newText,key)
            todoDao.update(todo)
            this.startActivity(intent)
        }
        cancelBtn.setOnClickListener {
            this.startActivity(intent)
        }


    }
}