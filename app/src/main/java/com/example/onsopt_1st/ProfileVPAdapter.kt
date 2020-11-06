package com.example.onsopt_1st


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ProfileVPAdapter (fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> FirstFragment()
        1 -> SecondFragment()

        else -> throw IllegalStateException("Unexpected position $position")
    }
    override fun getCount(): Int = 2
}