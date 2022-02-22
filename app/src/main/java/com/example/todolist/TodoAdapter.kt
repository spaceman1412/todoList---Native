package com.example.todolist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.entities.Todo
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.startActivity




class TodoAdapter(
    var todos: List<Todo>, val context: Context, val dao : TodoDao
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val checkBox: CheckBox = itemView.findViewById(R.id.CheckBox)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val key = todos[position].id
        holder.apply {
            textView.text = todos[position].title
            checkBox.isChecked = todos[position].isChecked
        }
        holder.itemView.setOnClickListener {
            val intent : Intent =  Intent(context,ItemEdit::class.java)
            val todo = dao.getTodoWithId(key)

            intent.putExtra("KEY",key)


            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}