package id.ac.ui.cs.mobileprogramming.michaelwh.lab4.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.R
import id.ac.ui.cs.mobileprogramming.michaelwh.lab4.adapter.TodoAdapter
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment: Fragment() {

    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        model.todos.observe(viewLifecycleOwner, { todos ->
            val todoAdapter = TodoAdapter(todos)
            rv_main.layoutManager = LinearLayoutManager(activity)
            rv_main.adapter = todoAdapter
        })
    }
}
