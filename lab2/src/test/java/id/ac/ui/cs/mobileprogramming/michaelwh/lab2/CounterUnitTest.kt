package id.ac.ui.cs.mobileprogramming.michaelwh.lab2

import id.ac.ui.cs.mobileprogramming.michaelwh.lab2.model.Counter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CounterUnitTest {
    @Test
    fun counter_first_isZero() {
        val counter = Counter()
        assertEquals(0, counter.counter)
    }

    @Test
    fun counter_addition_correct() {
        val counter = Counter()
        counter.increment()
        assertEquals(1, counter.counter)
    }
}