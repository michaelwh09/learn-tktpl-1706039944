package id.ac.ui.cs.mobileprogramming.michaelwh.lab2

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CounterInstrumentedTest {
    private lateinit var scenario: FragmentScenario<FirstFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer<FirstFragment>(null, R.style.AppTheme, null)
    }

    @Test
    fun firstRenderShouldBeZero() {
        onView(withId(R.id.textview_first)).check(matches(withText("0")))
    }

    @Test
    fun afterIncrementShouldOne() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.textview_first)).check(matches(withText("1")))
    }
}
