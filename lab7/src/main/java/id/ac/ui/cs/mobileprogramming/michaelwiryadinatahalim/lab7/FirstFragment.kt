package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*
import android.view.animation.Animation

import android.view.animation.ScaleAnimation




/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textview_counter.text = counter.toString()
        fab.setOnClickListener {
            val fadeIn =
                ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            fadeIn.duration = 350
            fadeIn.fillAfter = true
            counter++
            textview_counter.startAnimation(fadeIn)
            textview_counter.text = counter.toString()
        }
    }
}
