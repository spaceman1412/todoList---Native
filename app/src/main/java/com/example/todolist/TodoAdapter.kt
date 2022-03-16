package com.example.todolist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.entities.Todo


class TodoAdapter(
    var todos: List<Todo>, val context: Context, val dao: TodoDao
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val checkBox: CheckBox = itemView.findViewById(R.id.CheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val key = todos[position].id
        holder.apply {
            textView.text = todos[position].title
            checkBox.isChecked = todos[position].isChecked
        }
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, ItemEdit::class.java)
            val todo = dao.getTodoWithId(key)

            intent.putExtra("KEY", key)

            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete this note?") // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, which ->
                        // Continue with delete operation
                        val todo = dao.getTodoWithId(key)
                        dao.delete(todo)
                        todos = dao.getAll()
                        notifyItemRemoved(position)
                    }) // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}