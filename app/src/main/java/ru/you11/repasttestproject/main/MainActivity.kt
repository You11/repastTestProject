package ru.you11.repasttestproject.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import ru.you11.repasttestproject.R
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.main_pager)
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(this, R.drawable.baseline_explore_white_18)
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(this, R.drawable.baseline_favorite_white_18)
        tabLayout.getTabAt(2)?.icon = ContextCompat.getDrawable(this, R.drawable.baseline_account_circle_white_18)
    }
}

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> {
                val fragment = NearbyFragment()
                NearbyPresenter(fragment)
                fragment
            }
            1 -> {
                val fragment = FavoritesFragment()
                FavoritesPresenter(fragment)
                fragment
            }
            2 -> UserProfileFragment()
            else -> throw Exception("Fragment not found")
        }
    }

    override fun getCount(): Int = 3
}
