package com.adamstyrc.winamp.ui.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.adamstyrc.winamp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DashboardActivityTest {

    @get:Rule var activityRule = ActivityTestRule(DashboardActivity::class.java)

    @Test
    fun performSearchOnDefaultSource() {
        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.searchTextView))
            .perform(typeText("door"), closeSoftKeyboard())

        onView(withId(R.id.rvSongs)).check(matches(isDisplayed()))
    }

}
