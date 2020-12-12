package id.ac.ui.cs.mobileprogramming.michaelwh.lab4.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.model.Todo

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

class MainViewModel : ViewModel() {
    private val todosItem = MutableLiveData<MutableList<Todo>>()

    val todos: LiveData<MutableList<Todo>>
        get() = todosItem

    init {
        todosItem.value = mutableListOf()
    }

    fun addTodo(todoText: String) {
        todosItem.value?.add(Todo(todoText))
        todosItem.notifyObserver()
    }
}
