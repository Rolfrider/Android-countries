package com.rolfrider.countries.activity

import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.countries.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.country_recycle_view_item.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun `click on country item starts CountryDetailActivity` (){

        val activity = Robolectric.setupActivity(MainActivity::class.java)

        val recyclerView = activity.findViewById<RecyclerView>(R.id.countryRecyclerView)
        recyclerView.measure(0,0)
        recyclerView.layout(0,0,100,1000)

        activity.country_item.performClick()

        Assert.assertEquals(
            CountryDetailActivity::class.java.name,
            Shadows.shadowOf(activity).nextStartedActivity?.component?.className
        )
    }

}