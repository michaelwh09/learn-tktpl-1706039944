package id.ac.ui.cs.mobileprogramming.michaelwh.lab2

import id.ac.ui.cs.mobileprogramming.michaelwh.lab2.model.Counter
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CounterUnitTest {

    private lateinit var counter: Counter

    @Before
    fun setup() {
        counter = Counter()
    }

    @Test
    fun counter_first_isZero() {
        assertEquals(0, counter.counter)
    }

    @Test
    fun counter_addition_correct() {
        counter.increment()
        assertEquals(1, counter.counter)
        counter.increment()
        assertEquals(2, counter.counter)
    }

    @Test
    fun counter_decrement_correct() {
        counter.decrement()
        assertEquals(-1, counter.counter)
        counter.decrement()
        assertEquals(-2, counter.counter)
    }
}
