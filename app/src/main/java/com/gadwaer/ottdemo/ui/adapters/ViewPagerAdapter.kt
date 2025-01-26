package com.gadwaer.ottdemo.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gadwaer.ottdemo.ui.homeui.HomeFragment
import com.gadwaer.ottdemo.ui.listingui.ListingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HomeFragment(),
        ListingFragment()
    )

    private val fragmentTitles = listOf("Home", "Movies")

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun getTitle(position: Int): String = fragmentTitles[position]
}
