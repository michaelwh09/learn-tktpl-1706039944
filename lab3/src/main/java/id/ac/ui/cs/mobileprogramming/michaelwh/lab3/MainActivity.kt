package id.ac.ui.cs.mobileprogramming.michaelwh.lab3

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var secondTap = false
    private val timeout = 2000
    private var seconds = 0
    private var running = false
    private var timer: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStart(view: View?) {
        if (running) return
        running = true
        timer = CoroutineScope(Dispatchers.Main).launch {
            runTimer()
        }
        materialButton1.isEnabled = false
    }

    fun onClickStop(view: View?) {
        stopJob()
        running = false
        materialButton1.isEnabled = true
    }

    fun onClickReset(view: View?) {
        stopJob()
        running = false
        seconds = 0
        setTimeText(time_view)
        materialButton1.isEnabled = true
    }

    private fun stopJob() {
        timer?.cancel()
    }

    override fun onBackPressed() {
        if (secondTap) {
            super.onBackPressed()
        } else {
            secondTap = true
            Snackbar.make(
                root_view, "Press back button again to close apps",
                timeout
            ).show()
            GlobalScope.launch {
                delay(timeout.toLong())
                secondTap = false
            }
        }
    }

    private suspend fun runTimer() = withContext(Dispatchers.Main) {
        while (true) {
            setTimeText(time_view)
            if (running) {
                seconds++
            }
            delay(1000L)
        }
    }

    private fun setTimeText(timeView: TextView) {
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        val secs = seconds % 60
        val time = String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d", hours,
            minutes, secs
        )
        timeView.text = time
    }
}
