package id.ac.ui.cs.mobileprogramming.michaelwh.lab4.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_first.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        button_add.setOnClickListener {
            val todoText = todo.editText?.text.toString().trim()
            val command = todoText.split(" ")
            if (command[0].equals("fib", ignoreCase = true) && command[1].isDigitsOnly()) {
                val result = fibonacci(Integer.parseInt(command[1]))
                model.addTodo("Fibonnaci ${command[1]} is $result")
            } else {
                model.addTodo(todoText)
            }
            Snackbar.make(main, "Successfully add $todoText to list", 1500).show()
            todo.editText?.clearFocus()
            todo.editText?.text?.clear()
        }
    }

    external fun fibonacci(input: Int): Int
}
