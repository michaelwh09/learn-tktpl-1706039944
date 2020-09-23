package id.ac.ui.cs.mobileprogramming.michaelwh.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.michaelwh.lab2.model.Counter
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val counter = Counter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            counter.increment()
            textview_first.text = counter.counter.toString()
        }
    }
}
