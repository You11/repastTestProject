package ru.you11.repasttestproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.main_pager)
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }
}

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> NearbyFragment()
            1 -> FavoritesFragment()
            2 -> UserProfileFragment()
            else -> throw Exception("Fragment not found")
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Nearby"
            1 -> "Favorites"
            2 -> "User"
            else -> ""
        }
    }
}
