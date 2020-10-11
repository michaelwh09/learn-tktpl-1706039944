package id.ac.ui.cs.mobileprogramming.michaelwh.lab4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.R
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.model.Todo

class TodoAdapter(private val todos: List<Todo>): RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    class TodoHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView:TextView = view.findViewById(R.id.textView)
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.textView.text = todos[position].textDo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return TodoHolder(view)
    }
}
