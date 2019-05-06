package com.rolfrider.countries.view.countrydetail

import android.content.Intent
import com.rolfrider.countries.view.countrylist.CountryItem
import kotlinx.android.synthetic.main.country_header.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
class CountryDetailActivityTest{

    private val countryItem = CountryItem("POL", "Poland", "polFlagImageLink")

    @Test
    fun `click on X button finish activity`(){
        val activity = Robolectric.buildActivity(CountryDetailActivity::class.java,
            Intent().putExtra(CountryDetailActivity.EXTRA_KEY,countryItem))
            .create().get()

        Assert.assertFalse("${activity.localClassName} should not be finished", activity.isFinishing)

        activity.closeButton.performClick()
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        Assert.assertTrue("${activity.localClassName} should be finished",  activity.isFinishing)
    }
}
